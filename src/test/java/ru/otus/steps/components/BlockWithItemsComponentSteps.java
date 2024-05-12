package ru.otus.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.contents.components.mainpage.BlockWithItemsComponent;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class BlockWithItemsComponentSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    @Inject
    private BlockWithItemsComponent blockWithItemsComponent;

    @Когда("^в меню преподавателей нажат пункт '(.*)'$")
    public void clickItemByName(String itemName) {
        itemName = guiceScenarioScoped.getVariablesUtil().evalText(itemName);
        blockWithItemsComponent.clickItemByName(itemName);
    }
}