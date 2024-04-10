package ru.otus.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.exceptions.WebDriverNotSupportedException;
import ru.otus.factory.impl.ChromeWebDriver;
import ru.otus.factory.impl.IDriver;
import ru.otus.listeners.ActionsListener;
import java.util.Locale;

public class DriverFactory implements IDriverFactory {
    private final String browserName = System.getProperty("browser.name").toLowerCase(Locale.ROOT);

    @Override
    public WebDriver getDriver() {
        switch (this.browserName) {
            case "chrome": {
                WebDriverManager.chromiumdriver().setup();
                IDriver<ChromeOptions> browserSettings = new ChromeWebDriver();
                return new EventFiringDecorator<>(new ActionsListener())
                        .decorate(new ChromeDriver(browserSettings.getDriverOptions()));
            }
            default:
                throw new WebDriverNotSupportedException(browserName);
        }
    }
}
