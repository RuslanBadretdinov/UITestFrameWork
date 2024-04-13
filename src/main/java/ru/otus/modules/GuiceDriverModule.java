package ru.otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.otus.factory.DriverFactory;

public class GuiceDriverModule extends AbstractModule {

    private final WebDriver driver = new DriverFactory().getDriver();
    private final Actions actions = new Actions(driver);

    @Provides
    public WebDriver getDriver() { return driver; }

    @Provides
    public Actions getActions() { return actions; }
}
