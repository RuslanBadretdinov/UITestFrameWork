package ru.otus.contents.pages.abstracts;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import ru.otus.annotations.PageValidation;
import ru.otus.scenario_scoped.GuiceScenarioScoped;
import ru.otus.utils.fromdriver.PageComponentUtil;

public abstract class AnyPageWithDynamicAnnotationAbs<T> extends AnyPageAbs<T> {
    protected String incomingValueForAnnotation;
    private By dynamicPageValidationBy;

    @Inject
    public AnyPageWithDynamicAnnotationAbs(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

    @Override
    public T isLoaded() {
        assertThat(incomingValueForAnnotation)
                .as("Не установлен входной параметр, который нужен для загрузки страницы")
                .isNotEmpty();
        return (T) this;
    }

    public T isLoaded(String titleOfItemPage) {
        setTitleOfItemPage(titleOfItemPage);
        String pageIsNotLoadedText = String.format("Страница '%s' под названием '%s' не загружена, "
                        + "или локатор маркера не присутствует на странице",
                this.getClass().getSimpleName(), titleOfItemPage);
        assertThat(this.waiter.waitForElementVisible(dynamicPageValidationBy))
                .as(pageIsNotLoadedText)
                .isTrue();
        super.isLoaded($(dynamicPageValidationBy));
        return (T) this;
    }

    public T setTitleOfItemPage(String titleOfItemPage) {
        this.incomingValueForAnnotation = titleOfItemPage;
        this.dynamicPageValidationBy = getDynamicPageValidationBy();
        return (T) this;
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
        return PageComponentUtil.defineLocatorTypeByAnnotationValue(locatorTypeAndLocator);
    }
}
