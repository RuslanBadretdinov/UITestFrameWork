package ru.otus.utils.fromdriver;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.otus.scenario_scoped.GuiceScenarioScoped;
import ru.otus.utils.fromdriver.waiters.Waiter;


public class CommonActions<T> {
    protected GuiceScenarioScoped guiceScenarioScoped;
    protected WebDriver driver;
    protected Waiter waiter;
    protected Actions actions;

    @Inject
    public CommonActions(GuiceScenarioScoped guiceScenarioScoped) {
        this.guiceScenarioScoped = guiceScenarioScoped;
        this.driver = guiceScenarioScoped.getDriver();
        this.waiter = guiceScenarioScoped.getWaiter();
        this.actions = guiceScenarioScoped.getActions();
        PageFactory.initElements(driver, this);
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

    protected String getText(WebElement webElement) {
        return webElement.getText();
    }
}