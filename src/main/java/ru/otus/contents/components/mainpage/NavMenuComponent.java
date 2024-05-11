package ru.otus.contents.components.mainpage;

import com.github.javafaker.Faker;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import ru.otus.annotations.Component;
import ru.otus.contents.components.abstracts.AnyComponentAbs;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

import java.util.stream.Collectors;

@Component("xpath://nav/div[span[text() = '%s']]")
public class NavMenuComponent extends AnyComponentAbs<NavMenuComponent> {

    private final String hrefXpath = "./following-sibling::div[1]//a";
    private final String hrefXpathClickName = "./following-sibling::div[1]//a[contains(text(), '%s')]";

    @Inject
    public NavMenuComponent(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

    @Override
    public NavMenuComponent setItemList() {
        this.itemList = this.waiter.waitForElementsVisible(getComponentEntity(this.incomingValueForAnnotation)
                .findElements(By.xpath(hrefXpath)));
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
        this.waiter.waitVisibleElementAndReturn(
                getComponentEntity(blockName).findElement(By.xpath(String.format(hrefXpathClickName, name)))
        ).click();
    }

    public void clickItemByName(String name) {
        this.clickItemByName(this.incomingValueForAnnotation, name);
    }
}
