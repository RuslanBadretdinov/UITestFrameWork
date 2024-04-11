package ru.otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.CourseItemPage;
import ru.otus.contents.pages.dynamics.CoursesDynamicVersionPage;
import ru.otus.contents.pages.dynamics.InstructorItemPage;
import ru.otus.factory.DriverFactory;

public class GuiceDriverModule extends AbstractModule {
    private final WebDriver driver = new DriverFactory().getDriver();

    @Provides
    public WebDriver getDriver() {
        return driver;
    }

    @Provides
    public CoursesPage getCoursesPage() {
        return new CoursesPage(driver);
    }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Provides
    @Singleton
    public CourseItemPage getCourseItemPage() {
        return new CourseItemPage(driver);
    }

    @Provides
    @Singleton
    public CoursesDynamicVersionPage getCoursesDynamicVersionPage() {
        return new CoursesDynamicVersionPage(driver);
    }

    @Provides
    @Singleton
    public InstructorItemPage getInstructorItemPage() {
        return new InstructorItemPage(driver);
    }
}
