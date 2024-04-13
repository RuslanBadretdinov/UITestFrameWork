package ru.otus.utils.fromdriver;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.otus.utils.fromdriver.waiters.Waiter;


public class CommonActions<T> {

    protected WebDriver driver;
    @Inject
    protected Waiter waiter;
    @Inject
    protected Actions actions;
    @Inject
    protected PageComponentUtil pageComponentUtil;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

    protected String getText(WebElement webElement) {
        return webElement.getText();
    }
}