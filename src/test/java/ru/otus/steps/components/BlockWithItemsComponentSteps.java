package ru.otus.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.contents.components.mainpage.BlockWithItemsComponent;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class BlockWithItemsComponentSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    private BlockWithItemsComponent getBlockWithItemsComponent() {
        return (BlockWithItemsComponent) guiceScenarioScoped.getComponentLibrary().getComponent(BlockWithItemsComponent.class);
    }

    @Когда("^в меню преподавателей нажат пункт '(.*)'$")
    public void clickItemByName(String itemName) {
        itemName = guiceScenarioScoped.getVariablesUtil().evalText(itemName);
        getBlockWithItemsComponent().clickItemByName(itemName);
    }
}