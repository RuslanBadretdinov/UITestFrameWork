package ru.otus.factory;

import org.openqa.selenium.WebDriver;
import ru.otus.exceptions.WebDriverNotSupportedException;

public interface IDriverFactory {
    WebDriver getDriver() throws WebDriverNotSupportedException;
}
