package ru.otus.contents.components.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.Component;
import ru.otus.utils.fromdriver.CommonActions;
import java.util.List;

public abstract class AnyComponentAbs<T> extends CommonActions<T> {
    protected String incomingValueForAnnotation;
    protected List<WebElement> itemList;

    public AnyComponentAbs(WebDriver driver) {
        super(driver);
    }

    protected WebElement getComponentEntity(String incomingValueForAnnotation) {
        this.addValueForAnnotation(incomingValueForAnnotation);
        return $(getComponentLocator());
    }

    protected By getComponentLocator() {
        Component component = getClass().getAnnotation(Component.class);

        if (component != null) {
            String value = component.value();

            if (this.incomingValueForAnnotation.isEmpty()) return null;

            value = String.format(value, this.incomingValueForAnnotation);
            return this.pageComponentUtil.defineLocatorTypeByAnnotationValue(value);
        }
        return null;
    }

    public T chooseNeededBlockAndMoveToThis(String blockName) {
        this.actions.moveToElement(getComponentEntity(blockName)).build().perform();
        return (T) this;
    }

    public abstract T setItemList();


    public T chooseNeededBlockAndSetItemList(String blockName) {
        chooseNeededBlockAndMoveToThis(blockName);
        setItemList();
        return (T) this;
    }

    public List<WebElement> getItemList() {
        return itemList;
    }

    protected WebElement getItemWebElement(int index) {
        return itemList.get(--index);
    }

    protected WebElement getItemParameterWebElementByIndex(WebElement item, String xpathValue, int indexParameter) {
        return item.findElement(By.xpath(String.format(xpathValue + "[%d]", indexParameter)));
    }

    private void addValueForAnnotation(String incomingValueForAnnotation) {
        this.incomingValueForAnnotation = incomingValueForAnnotation;
    }
}
