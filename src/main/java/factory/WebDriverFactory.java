package factory;

import exceptions.WebDriverNotSupportedException;
import factory.impl.ChromeSettings;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory {
    private final String BROWSER_NAME = System.getProperty("browser.name");

    public EventFiringWebDriver create() {
        switch (BROWSER_NAME.toLowerCase(Locale.ROOT)) {
            case "chrome" : {
                return new EventFiringWebDriver(new ChromeDriver(new ChromeSettings().getSettings()));
            }
            case "firefox" : {
                throw new WebDriverNotSupportedException(BROWSER_NAME);
            }
        }

        throw new WebDriverNotSupportedException(BROWSER_NAME);
    }
}
