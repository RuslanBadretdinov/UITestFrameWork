package ru.otus.contents.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AnyPageWithDynamicAnnotationAbs<T> extends AnyPageAbs<T>{
    private String incomingValueForAnnotation;
    private By dynamicPageValidationBy;

    public AnyPageWithDynamicAnnotationAbs(WebDriver driver) {
        super(driver);
    }

    @Override
    public T isLoaded() {
        assertThat(incomingValueForAnnotation)
                .as("Не установлен входной параметр, определяющий, который нужгл было найти при загрузке страницы")
                .isNotEmpty();
        return super.isLoaded();
    }

    public T isLoaded(String titleOfItemPage) {
        setTitleOfItemPage(titleOfItemPage);
        super.isLoaded($(dynamicPageValidationBy));
        return (T) this;
    }

    public void setTitleOfItemPage(String titleOfItemPage) {
        this.incomingValueForAnnotation = titleOfItemPage;
        this.dynamicPageValidationBy = getDynamicPageValidationBy();
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
}
