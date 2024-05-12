package ru.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.contents.pages.IDynamicPage;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

import static org.junit.jupiter.api.Assertions.fail;

public class AllPageSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    @Когда("^открыта по URL и загружена страница '(.*)'$")
    public void openViaURLAndLoaded(String pageName) {
        guiceScenarioScoped.getPageLibrary().getPage(pageName).open().isLoaded();
    }

    @Когда("^открыта страница по URL '(.*)'$")
    public void openViaURL(String pageName) {
        guiceScenarioScoped.getPageLibrary().getPage(pageName).open();
    }

    @Когда("^загружена страница '(.*)'$")
    public void open(String pageName) {
        guiceScenarioScoped.getPageLibrary().getPage(pageName).isLoaded();
    }

    @Когда("^загружена динамическая страница '(.*)' c заголовком '(.*)'$")
    public void open(String pageName, String title) {
        IDynamicPage<? extends AnyPageWithDynamicAnnotationAbs<?>> dynamicPage =
                (IDynamicPage<? extends AnyPageWithDynamicAnnotationAbs<?>>) guiceScenarioScoped.getPageLibrary().getPage(pageName);
        if (!(dynamicPage instanceof AnyPageWithDynamicAnnotationAbs)) {
            String failMessage = String.format("это страница '%s' не динамическая", pageName);
            fail(failMessage);
        }
        title = guiceScenarioScoped.getVariablesUtil().evalText(title);
        dynamicPage.isLoaded(title);
    }
}
