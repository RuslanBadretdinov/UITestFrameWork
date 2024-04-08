package ru.otus.contents.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.Component;

import java.util.List;

@Component("xpath://section[.//*[text()='%s']]")
public class BlockWithItemsComponent extends AnyComponentAbs<BlockWithItemsComponent> {

    @Inject
    public BlockWithItemsComponent(WebDriver driver, String blockTitle) {
        super(driver, blockTitle);
    }

    private final List<WebElement> itemList = getComponentEntity().findElements(By.xpath(".//a[./div]"));

    public void clickItem(String name) {
        getComponentEntity().findElement(By.xpath(String.format(".//[.//div[text()='%s']]", name))).click();
    }

    public String getItemParameterText(int indexItem, int indexParameter) {
        return this.getItemParameter(this.getItem(indexItem), indexParameter).getText();
    }

    public WebElement getItemParameter(WebElement item, int indexParameter) {
        return item.findElement(By.xpath(String.format(".//div[%d]",indexParameter)));
    }

    public WebElement getItem(int index) {
        WebElement itemElement = index == 0 ? itemList.get(index) : itemList.get(--index);
        return itemElement;
    }
}
