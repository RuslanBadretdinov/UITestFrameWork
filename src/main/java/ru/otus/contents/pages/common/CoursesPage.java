package ru.otus.contents.pages.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageAbs;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@UrlPrefix("/catalog/courses")
public class CoursesPage extends AnyPageAbs<CoursesPage> {

    private final String hrefXpath = "./.";
    private final String nameXpath = ".//h6";
    private final String dateXpath = ".//h6/following-sibling::div//div[text()]";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd MMMM, yyyy", new Locale("ru"));

    @FindBy(xpath = "//main//h1[.//*[text()='Каталог']]")
    private WebElement isLoadedElement;

    @FindBy(xpath = "//section[.//h1//div[text()='Каталог']]")
    private WebElement coursesBlock;
    private List<WebElement> coursesList;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoursesPage isLoaded() {
        this.isLoaded(this.isLoadedElement);
        return this;
    }

    public CoursesPage checkDataOfCoursesListWithDataOfCoursePageViaJSOUP() {
        this.coursesList
                .forEach(el -> {
                    String href = el.findElement(By.xpath(hrefXpath)).getAttribute("href");
                    String courseName = el.findElement(By.xpath(nameXpath)).getAttribute("innerText").trim();
                    LocalDate elDate = parseDateToLocaleDate(el.findElement(By.xpath(dateXpath)).getText().trim());

                    Jsoup.newSession();
                    Document coursePage = null;
                    try {
                        coursePage = Jsoup.connect(href).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assertThat(
                            coursePage.selectXpath("//h1").stream()
                                    .map(element -> element.text().trim())
                                    .anyMatch(x -> x.equals(courseName))
                    )
                            .as(String.format("Нужного заголовка '%s' курса не было найдено на '%s'",
                                    courseName, href))
                            .isTrue();

                    assertThat(
                            coursePage.selectXpath("//section//div[contains(@class, 'galmep')]/div/div[1]//p "
                                            + "| //p[contains(text(), 'Начало занятий')]/../../..//p[contains(@class, 'item-text')]")
                                    .stream()
                                    .map(element -> element.text().trim())
                                    .anyMatch(x -> this.parseDateToLocaleDate(x + ", 2024").equals(elDate))
                    )
                            .as(String.format("Нужной даты '%s' начала курса не было найдено на '%s'",
                                    elDate.format(dateTimeFormatter), href))
                            .isTrue();
                });
        return this;
    }

    public CoursesPage setTheEarliestCoursesList() {
        setSortedDateCoursesList(false);
        return this;
    }

    public CoursesPage setTheLatestCoursesList() {
        setSortedDateCoursesList(true);
        return this;
    }

    private void setSortedDateCoursesList(boolean trueIsMaxFalseIsMin) {
        LocalDate filterDate = this.coursesBlock.findElements(By.xpath(dateXpath)).stream()
                .map(el -> parseDateToLocaleDate(el.getText()))
                .min((l1, l2) -> trueIsMaxFalseIsMin ? l2.compareTo(l1) : l1.compareTo(l2)).get();

        this.coursesList = this.coursesBlock.findElements(By.xpath(dateXpath)).stream()
                .filter(el -> filterDate.compareTo(parseDateToLocaleDate(el.getText())) == 0)
                .map(o -> o.findElement(By.xpath("./ancestor-or-self::a")))
                .collect(Collectors.toList());
    }

    private LocalDate parseDateToLocaleDate(String text) {
        return LocalDate.parse(text.replaceAll("\\s*·.*$", "").trim(), dateTimeFormatter);
    }

    public void clickNeededCourse(String courseName) {
        coursesBlock.findElements(By.xpath(nameXpath))
                .stream()
                .filter(element -> element.getAttribute("innerText").equals(courseName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Course '%s' not found", courseName)))
                .click();
    }
}