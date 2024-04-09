package ru.otus.contents.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.UrlPrefix;
import ru.otus.utils.fromdriver.CommonActions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AnyPageAbs<T> extends CommonActions<T> {

    protected WebElement staticElementViaFindBy;
    private By dynamicPageValidationBy;

    public AnyPageAbs(WebDriver driver) {
        super(driver);
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) this;
    }

    public T isLoaded() {
        this.isLoaded(staticElementViaFindBy);
        return (T) this;
    }

    protected T isLoaded(WebElement elementViaFindBy) {
        String pageIsNotLoadedText = String.format("Страница '%s' не загружена, вебэлемент маркера не виден на странице",
                this.getClass().getSimpleName());
        assertThat(waiter.waitForElementVisible(elementViaFindBy)).as(pageIsNotLoadedText).isTrue();
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

    protected String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("base.url"), "/");
    }

    protected String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }
        return "";
    }
}
