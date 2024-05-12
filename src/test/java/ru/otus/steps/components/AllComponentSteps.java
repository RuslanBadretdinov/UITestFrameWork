package ru.otus.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class AllComponentSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    @Когда("^в меню '(.*)' наведена мышь на категорию '(.*)'$")
    public void openViaURLAndLoadedStep(String componentName, String categoryName) {
        categoryName = guiceScenarioScoped.getVariablesUtil().evalText(categoryName);
        guiceScenarioScoped.getComponentLibrary().getComponent(componentName).chooseNeededBlockAndMoveToThis(categoryName);
    }

    @Когда("^в меню '(.*)' установлен список пунктов$")
    public void setItemList(String componentName) {
        guiceScenarioScoped.getComponentLibrary().getComponent(componentName).setItemList();
    }

    @Когда("^в меню '(.*)' с помощью наведения мыши выбрана категория '(.*)' с сохранением списка пунктов$")
    public void chooseNeededBlockAndSetItemList(String componentName, String categoryName) {
        categoryName = guiceScenarioScoped.getVariablesUtil().evalText(categoryName);
        guiceScenarioScoped.getComponentLibrary().getComponent(componentName).chooseNeededBlockAndSetItemList(categoryName);
    }

    @Когда("^в меню '(.*)' в категории '(.*)' нажат пункт '(.*)'$")
    public void clickItemByName(String componentName, String categoryName, String itemName) {
        categoryName = guiceScenarioScoped.getVariablesUtil().evalText(categoryName);
        itemName = guiceScenarioScoped.getVariablesUtil().evalText(itemName);
        guiceScenarioScoped.getComponentLibrary().getComponent(componentName).clickItemByName(categoryName, itemName);
    }
}