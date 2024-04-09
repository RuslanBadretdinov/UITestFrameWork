package ru.otus.contents.pages.common;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageAbs;

import java.util.List;
import java.util.NoSuchElementException;

@UrlPrefix("/catalog/courses")
public class CoursesPage extends AnyPageAbs<CoursesPage> {

    @FindBy(xpath = "//main//h1[.//*[text()='Каталог']]")
    private WebElement isLoadedElement;

    @FindBy(xpath = "//section[.//h1//div[text()='Каталог']]")
    private WebElement coursesBlock;
    private List<WebElement> coursesList;

    @Inject
    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoursesPage isLoaded() {
        this.isLoaded(this.isLoadedElement);
        return this;
    }

    public CoursesPage setCoursesList() {
        this.coursesList = coursesBlock.findElements(By.xpath(".//div[./a]/a/h6"));
        return this;
    }

    public void clickNeededCourse(String courseName) {
        coursesList.stream()
                .filter(element -> element.getAttribute("innerText").equals(courseName))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException(String.format("Course '%s' not found", courseName)))
                .click();
    }
}