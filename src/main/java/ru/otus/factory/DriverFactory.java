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
    private final String BROWSER_NAME = System.getProperty("browser.name").toLowerCase(Locale.ROOT);
    private final String BROWSER_VERSION = System.getProperty("browser.version").toLowerCase(Locale.ROOT);

    @Override
    public WebDriver getDriver() {
        switch (this.BROWSER_NAME) {
            case "chrome": {
                WebDriverManager.chromiumdriver().browserVersion(BROWSER_VERSION).setup();
                ;
                IDriver<ChromeOptions> browserSettings = new ChromeWebDriver();
                return new EventFiringDecorator<>(new ActionsListener())
                        .decorate(new ChromeDriver(browserSettings.getDriverOptions()));
            }
            default:
                throw new WebDriverNotSupportedException(BROWSER_NAME);
        }
    }
}
