package ru.otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import ru.otus.utils.fromdriver.PageComponentUtil;
import ru.otus.utils.fromdriver.waiters.Waiter;

public class GuiceUtilsModule extends AbstractModule {

    @Inject
    private WebDriver driver;

    @Provides
    @Singleton
    public Waiter getWaiter() {
        return new Waiter(driver);
    }

    @Provides
    @Singleton
    public PageComponentUtil getPageComponentUtil() {
        return new PageComponentUtil();
    }
}
