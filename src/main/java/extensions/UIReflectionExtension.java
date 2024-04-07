package extensions;

import annotations.Driver;
import factory.WebDriverFactory;
import listeners.WebDriverListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Arrays;

public class UIReflectionExtension implements BeforeEachCallback, AfterEachCallback {

    private WebDriver driver = null;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        EventFiringWebDriver driver = new WebDriverFactory().create();
        driver.register(new WebDriverListener());
        assignDriverVars(extensionContext, driver);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
    
    private void assignDriverVars(ExtensionContext extensionContext, EventFiringWebDriver driver) {
        Arrays.stream(extensionContext.getTestInstance().getClass()
                        .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Driver.class) && field.getType().equals(WebDriver.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(extensionContext.getTestInstance(), driver);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
