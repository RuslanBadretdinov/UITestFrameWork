package ru.otus.driver.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.otus.driver.utils.waiters.Waiter;


public class CommonActions<T> {

    protected WebDriver driver;
    protected Waiter waiter;
    protected Actions actions;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waiter = new Waiter(driver);
        actions = new Actions(driver);
    }

    protected PageComponentUtil getPageComponentUtil() {
        return PageComponentUtil.getInstance();
    }

    protected Waiter getWaiter() {
        return waiter;
    }

    protected Actions getActions() {
        return actions;
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

    protected String getText(WebElement webElement) {
        return webElement.getText();
    }
}