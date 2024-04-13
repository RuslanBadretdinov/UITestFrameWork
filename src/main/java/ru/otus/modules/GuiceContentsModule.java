package ru.otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import ru.otus.contents.components.mainpage.BlockWithItemsComponent;
import ru.otus.contents.components.mainpage.NavMenuComponent;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.CourseItemPage;
import ru.otus.contents.pages.dynamics.CoursesDynamicVersionPage;
import ru.otus.contents.pages.dynamics.InstructorItemPage;

public class GuiceContentsModule extends AbstractModule {

    @Inject
    private WebDriver driver;

    @Provides
    @Singleton
    public BlockWithItemsComponent getBlockWithItemsComponent() {
        return new BlockWithItemsComponent(driver);
    }

    @Provides
    @Singleton
    public NavMenuComponent getNavMenuComponent() {
        return new NavMenuComponent(driver);
    }

    @Provides
    @Singleton
    public CoursesDynamicVersionPage getCoursesDynamicVersionPage() {
        return new CoursesDynamicVersionPage(driver);
    }

    @Provides
    @Singleton
    public CourseItemPage getCourseItemPage() {
        return new CourseItemPage(driver);
    }

    @Provides
    @Singleton
    public InstructorItemPage getInstructorItemPage() {
        return new InstructorItemPage(driver);
    }

    @Provides
    @Singleton
    public CoursesPage getCoursesPage() { return new CoursesPage(driver); }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(driver);
    }
}
