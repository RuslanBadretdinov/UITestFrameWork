package ru.otus.contents.components.mainpage;

import com.github.javafaker.Faker;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.Component;
import ru.otus.contents.components.abstracts.AnyComponentAbs;

import java.util.stream.Collectors;

@Component("xpath://nav/div[span[text() = '%s']]")
public class NavMenuComponent extends AnyComponentAbs<NavMenuComponent> {

    private final String HREF_XPATH = "./following-sibling::div[1]//a";
    private final String HREF_XPATH_CLICK_NAME = "./following-sibling::div[1]//a[contains(text(), '%s')]";

    @Inject
    public NavMenuComponent(WebDriver driver) {
        super(driver);
    }

    @Override
    public NavMenuComponent setItemList() {
        this.itemList = waiter.waitForElementsVisible(getComponentEntity(this.incomingValueForAnnotation)
                .findElements(By.xpath(HREF_XPATH)));
        return this;
    }

    public NavMenuComponent resetItemListViaLinkContainsPath(String linkContainsPath) {
        this.itemList = this.itemList.stream()
                .filter(el -> el.getAttribute("href").contains(linkContainsPath))
                .collect(Collectors.toList());
        return this;
    }

    public String getRandomItemName() {
        return new Faker().options().nextElement(this.itemList).getText().replaceAll("\\(\\d*\\)", "").trim();
    }

    public void clickItemByName(String blockName, String name) {
        chooseNeededBlockAndMoveToThis(blockName);
        waiter.waitVisibleElementAndReturn(
                getComponentEntity(blockName).findElement(By.xpath(String.format(HREF_XPATH_CLICK_NAME, name)))
        ).click();
    }

    public void clickItemByName(String name) {
        this.clickItemByName(this.incomingValueForAnnotation, name);
    }
}
