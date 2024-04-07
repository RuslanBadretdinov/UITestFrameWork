package ru.otus.driver_utils.waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface IWaiter {
    boolean waitForCondition(ExpectedCondition condition);
}
