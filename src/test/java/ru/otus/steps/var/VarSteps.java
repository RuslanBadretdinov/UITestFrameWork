package ru.otus.steps.var;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class VarSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    @Когда("^в переменной '(.*)' сохранено значение '(.*)'$")
    public void setVarWithValue(String var, String value) {
        guiceScenarioScoped.getVariablesUtil().setVarWithValue(var, value);
    }
}