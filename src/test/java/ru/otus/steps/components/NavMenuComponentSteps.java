package ru.otus.steps.components;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.contents.components.mainpage.NavMenuComponent;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class NavMenuComponentSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    private NavMenuComponent getNavMenuComponent() {
        return (NavMenuComponent) guiceScenarioScoped.getComponentLibrary().getComponent(NavMenuComponent.class);
    }

    @Когда("^в навигационном меню отфильтрованы пункты содержащие ссылку на курс '(.*)'$")
    public void resetItemListViaLinkContainsPath(String href) {
        getNavMenuComponent().resetItemListViaLinkContainsPath(href);
    }

    @Когда("^в навигационном меню в переменной '(.*)' сохранено название случайного курса$")
    public void getRandomItemName(String varName) {
        guiceScenarioScoped.getVariablesUtil().setVarWithValue(varName, getNavMenuComponent().getRandomItemName());
    }

    @Когда("^в навигационном меню нажат пункт '(.*)'$")
    public void clickItemByName(String itemName) {
        itemName = guiceScenarioScoped.getVariablesUtil().evalText(itemName);
        getNavMenuComponent().clickItemByName(itemName);
    }
}