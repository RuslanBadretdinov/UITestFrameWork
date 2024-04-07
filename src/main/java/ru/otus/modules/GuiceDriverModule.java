package ru.otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import ru.otus.factory.DriverFactory;

public class GuiceDriverModule extends AbstractModule {
    private final WebDriver driver = new DriverFactory().getDriver();

    @Provides
    public WebDriver getDriver() {
        return driver;
    }
}
