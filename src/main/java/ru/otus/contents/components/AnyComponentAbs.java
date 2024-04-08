package ru.otus.contents.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.Component;
import ru.otus.driver.utils.CommonActions;

public abstract class AnyComponentAbs <T> extends CommonActions<T> {

    public AnyComponentAbs(WebDriver driver, String incomingValueForAnnotation) {
        super(driver);
        this.incomingValueForAnnotation = incomingValueForAnnotation;
    }

    public WebElement getComponentEntity() {
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
}
