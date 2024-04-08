package ru.otus.utils.fromdriver.waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface IWaiter {
    boolean waitForCondition(ExpectedCondition condition);
}
