package ru.otus.contents.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.Component;

@Component("xpath://section[.//*[text()='%s']]")
public class BlockWithItemsComponent extends AnyComponentAbs<BlockWithItemsComponent> {

    @Inject
    public BlockWithItemsComponent(WebDriver driver) {
        super(driver);
    }

    public void clickItemByName(String blockName, String name) {
        getComponentEntity(blockName).findElement(By.xpath(String.format(".//[.//div[text()='%s']]", name))).click();
    }

    public void clickItemByName(String name) {
        this.clickItemByName(this.incomingValueForAnnotation, name);
    }

    public String getItemParameterTextByIndex(int indexItem, int indexParameter) {
        return this.getItemParameterByIndex(this.getItemWebElement(indexItem), indexParameter).getText();
    }

    private WebElement getItemParameterByIndex(WebElement item, int indexParameter) {
        indexParameter--;
        return item.findElement(By.xpath(String.format(".//div[%d]",indexParameter)));
    }
}
