package ru.otus.steps.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.otus.contents.components.ComponentLibrary;
import ru.otus.contents.components.IComponent;
import ru.otus.contents.components.abstracts.AnyComponentAbs;
import ru.otus.contents.components.mainpage.BlockWithItemsComponent;
import ru.otus.contents.components.mainpage.NavMenuComponent;
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

    @Inject
    private BlockWithItemsComponent blockWithItemsComponent;
    @Inject
    private NavMenuComponent navMenuComponent;

    @Before
    public void before() {
        guiceScenarioScoped.setPageLibrary(new PageLibrary(createIPageMap()));
        guiceScenarioScoped.setComponentLibrary(new ComponentLibrary(createIComponentMap()));
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
            put("Каталог курсов", coursesPage);
            put("Главная страница", mainPage);
            put("Страница курса", courseItemPage);
            put("Динамическая версия страницы каталога курсов", coursesDynamicVersionPage);
            put("Страница инструктора", instructorItemPage);
        }};
    }

    private Map<String, IComponent<? extends AnyComponentAbs<?>>> createIComponentMap() {
        return new HashMap<>() {{
            put("Меню преподавателей", blockWithItemsComponent);
            put("Навигационное меню", navMenuComponent);
        }};
    }
}
