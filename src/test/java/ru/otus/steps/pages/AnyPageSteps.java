package ru.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class AnyPageSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    @Когда("^открыта по URL и загружена страница '(.*)'$")
    public void openViaURLAndLoaded(String pageName) {
        guiceScenarioScoped.getPageLibrary().getPage(pageName).open().isLoaded();
    }

    @Когда("^открыта по URL '(.*)'$")
    public void openViaURL(String pageName) {
        guiceScenarioScoped.getPageLibrary().getPage(pageName).open();
    }

    @Когда("^загружена страница '(.*)'$")
    public void open(String pageName) {
        guiceScenarioScoped.getPageLibrary().getPage(pageName).isLoaded();
    }
}
