package ru.otus.steps.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.otus.contents.pages.IPage;
import ru.otus.contents.pages.PageLibrary;
import ru.otus.contents.pages.abstracts.AnyPageAbs;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.CourseItemPage;
import ru.otus.contents.pages.dynamics.CoursesDynamicVersionPage;
import ru.otus.contents.pages.dynamics.InstructorItemPage;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

import java.util.HashMap;
import java.util.Map;

public class Hooks {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;

    @Inject
    private CoursesPage coursesPage;
    @Inject
    private MainPage mainPage;
    @Inject
    private CourseItemPage courseItemPage;
    @Inject
    private CoursesDynamicVersionPage coursesDynamicVersionPage;
    @Inject
    private InstructorItemPage instructorItemPage;

    @Before
    public void before() {
        guiceScenarioScoped.setPageLibrary(new PageLibrary(createIPageMap()));
    }

    @After
    public void after() {
        if (guiceScenarioScoped.getDriver() != null) {
            guiceScenarioScoped.getDriver().close();
            guiceScenarioScoped.getDriver().quit();
        }
    }

    private Map<String, IPage<? extends AnyPageAbs<?>>> createIPageMap() {
        return new HashMap<>() {{
            put("CoursesPage", coursesPage);
            put("Главная страница", mainPage);
            put("CourseItemPage", courseItemPage);
            put("CoursesDynamicVersionPage", coursesDynamicVersionPage);
            put("InstructorItemPage", instructorItemPage);
        }};
    }
}
