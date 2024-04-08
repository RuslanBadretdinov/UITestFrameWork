package ru.otus.contents.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.driver.utils.CommonActions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AnyPageAbs<T> extends CommonActions<T> {

    protected WebElement staticElementViaFindBy;
    private By dynamicPageValidationBy;

    public AnyPageAbs(WebDriver driver) {
        super(driver);
    }

    public AnyPageAbs(WebDriver driver, String incomingValueForAnnotation) {
        super(driver);
        this.incomingValueForAnnotation = incomingValueForAnnotation;

        this.dynamicPageValidationBy = getDynamicPageValidationBy();
        if (this.dynamicPageValidationBy == null) return;
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) this;
    }

    public T isLoaded() {
        assertThat(waiter.waitForElementVisible($(this.dynamicPageValidationBy)))
                .as("Error")
                .isTrue();

        return (T) this;
    }

    public <T> T page(Class<T> clazz) {
        try {
            Constructor constructor = clazz.getConstructor(WebDriver.class);

            return convertInstanceOfObject(constructor.newInstance(driver), clazz);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }


    private By getDynamicPageValidationBy() {
        if (getClass().isAnnotationPresent(PageValidation.class)) {
            PageValidation pageValidation = getClass().getAnnotation(PageValidation.class);
            String locatorTypeAndLocator = "";
            if (pageValidation.value().startsWith("template:")) {
                locatorTypeAndLocator = pageValidation.value().replace("template:", "");
            }
            return getPageLocator(locatorTypeAndLocator);
        }
        return null;
    }

    private By getPageLocator(String locatorTypeAndLocator) {
        if (locatorTypeAndLocator.isEmpty()) return null;
        locatorTypeAndLocator = String.format(locatorTypeAndLocator, this.incomingValueForAnnotation);
        return this.getPageComponentUtil().defineLocatorTypeByAnnotationValue(locatorTypeAndLocator);
    }

    private String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url", "https://otus.ru"), "/");
    }

    private String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }
        return "";
    }
}
