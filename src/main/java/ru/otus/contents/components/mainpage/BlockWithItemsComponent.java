package ru.otus.contents.components.mainpage;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.Component;
import ru.otus.contents.components.abstracts.AnyComponentAbs;

@Component("xpath://section[.//*[text()='%s']]")
public class BlockWithItemsComponent extends AnyComponentAbs<BlockWithItemsComponent> {

    private final String HREF_XPATH = ".//a[./div]";

    @Inject
    public BlockWithItemsComponent(WebDriver driver) {
        super(driver);
    }

    public void clickItemByName(String blockName, String name) {
        getComponentEntity(blockName).findElement(By.xpath(String.format(".//*[./div[text()='%s']]", name))).click();
    }

    public BlockWithItemsComponent setItemList() {
        this.itemList = waiter.waitForElementsVisible(getComponentEntity(this.incomingValueForAnnotation)
                .findElements(By.xpath(HREF_XPATH)));
        return this;
    }

    public void clickItemByName(String name) {
        this.clickItemByName(this.incomingValueForAnnotation, name);
    }

    public String getItemWithTextParameterAndIndex(int indexItem, int indexParameter) {
        return this.getItemParameterWebElementByIndex(
                        this.getItemWebElement(indexItem),
                        ".//div[text()]",
                        indexParameter)
                .getText();
    }
}
