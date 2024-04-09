package ru.otus.factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeWebDriver implements IDriver<ChromeOptions> {

    @Override
    public ChromeOptions getDriverOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser"));
        return chromeOptions;
    }
}
