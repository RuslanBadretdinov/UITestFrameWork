package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IWebDriverSettings{
    @Override
    public MutableCapabilities getSettings() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-errors");
        return chromeOptions;
    }
}
