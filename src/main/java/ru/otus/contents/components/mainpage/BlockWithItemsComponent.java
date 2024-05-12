package ru.otus.contents.components.mainpage;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import ru.otus.annotations.Component;
import ru.otus.contents.components.abstracts.AnyComponentAbs;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

@Component("xpath://section[.//*[text()='%s']]")
public class BlockWithItemsComponent extends AnyComponentAbs<BlockWithItemsComponent> {

    private final String hrefXpath = ".//a[./div]";

    @Inject
    public BlockWithItemsComponent(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

    public void clickItemByName(String name) {
        this.clickItemByName(this.incomingValueForAnnotation, name);
    }

    public void clickItemByName(String blockName, String name) {
        getComponentEntity(blockName).findElement(By.xpath(String.format(".//*[./div[text()='%s']]", name))).click();
    }

    @Override
    public BlockWithItemsComponent setItemList() {
        this.itemList = this.waiter.waitForElementsVisible(getComponentEntity(this.incomingValueForAnnotation)
                .findElements(By.xpath(hrefXpath)));
        return this;
    }

    public String getItemWithTextParameterAndIndex(int indexItem, int indexParameter) {
        return this.getItemParameterWebElementByIndex(
                        this.getItemWebElement(indexItem),
                        ".//div[text()]",
                        indexParameter)
                .getText();
    }
}
