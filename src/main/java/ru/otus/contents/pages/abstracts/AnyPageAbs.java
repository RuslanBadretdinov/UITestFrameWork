package ru.otus.contents.pages.abstracts;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.UrlPrefix;
import ru.otus.utils.fromdriver.CommonActions;


public abstract class AnyPageAbs<T> extends CommonActions<T> {

    public AnyPageAbs(WebDriver driver) {
        super(driver);
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) this;
    }

    public abstract T isLoaded();

    protected T isLoaded(WebElement elementViaFindBy) {
        String pageIsNotLoadedText = String.format("Страница '%s' не загружена, вебэлемент маркера не виден на странице",
                this.getClass().getSimpleName());
        assertThat(waiter.waitForElementVisible(elementViaFindBy)).as(pageIsNotLoadedText).isTrue();
        return (T) this;
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
