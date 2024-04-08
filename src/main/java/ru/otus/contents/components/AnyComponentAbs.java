package ru.otus.contents.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.Component;
import ru.otus.utils.fromdriver.CommonActions;

import java.util.List;

public abstract class AnyComponentAbs<T> extends CommonActions<T> {
    protected String incomingValueForAnnotation;
    private List<WebElement> itemList;

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
            return this.getPageComponentUtil()
                    .defineLocatorTypeByAnnotationValue(value);
        }
        return null;
    }

    public T chooseNeededBlockAndSetItemList(String blockName) {
        this.itemList = getComponentEntity(blockName).findElements(By.xpath(".//a[./div]"));
        return (T) this;
    }

    protected WebElement getItemWebElement(int index) {
        return itemList.get(--index);
    }

    private void addValueForAnnotation(String incomingValueForAnnotation) {
        this.incomingValueForAnnotation = incomingValueForAnnotation;
    }
}
