package ru.otus.scenario_scoped;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.otus.contents.components.ComponentLibrary;
import ru.otus.contents.pages.PageLibrary;
import ru.otus.factory.DriverFactory;
import ru.otus.utils.VariablesUtil;
import ru.otus.utils.fromdriver.waiters.Waiter;

@ScenarioScoped
public class GuiceScenarioScoped {
    private final WebDriver driver = new DriverFactory().getDriver();
    private final Actions actions = new Actions(driver);
    private final Waiter waiter = new Waiter(driver);
    private PageLibrary pageLibrary;
    private ComponentLibrary componentLibrary;
    private VariablesUtil variablesUtil = new VariablesUtil();

    public WebDriver getDriver() { return driver; }

    public Actions getActions() { return actions; }

    public Waiter getWaiter() { return waiter; }

    public PageLibrary getPageLibrary() { return pageLibrary; }
    public void setPageLibrary(PageLibrary pageLibrary) { this.pageLibrary = pageLibrary; }

    public ComponentLibrary getComponentLibrary() { return componentLibrary; }
    public void setComponentLibrary(ComponentLibrary componentLibrary) { this.componentLibrary = componentLibrary; }

    public VariablesUtil getVariablesUtil() { return variablesUtil; }
}
