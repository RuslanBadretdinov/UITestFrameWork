package ru.otus.contents.pages;

import com.google.inject.Inject;
import ru.otus.contents.pages.abstracts.AnyPageAbs;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.CourseItemPage;
import ru.otus.contents.pages.dynamics.CoursesDynamicVersionPage;
import ru.otus.contents.pages.dynamics.InstructorItemPage;

import java.util.HashMap;
import java.util.Map;

public class PageLibrary {

    private static PageLibrary INSTANCE;

    @Inject
    private MainPage mainPage;
    @Inject
    private CoursesPage coursesPage;
    @Inject
    private CourseItemPage courseItemPage;
    @Inject
    private CoursesDynamicVersionPage coursesDynamicVersionPage;
    @Inject
    private InstructorItemPage instructorItemPage;

    private Map<String, ? extends AnyPageAbs<?>> pageMap = new HashMap<>() {{
        put("Главная страница", mainPage);
    }};

    private PageLibrary() {
    }

    public static PageLibrary getInstance() {
        if (INSTANCE == null) { INSTANCE = new PageLibrary(); }
        return INSTANCE;
    }

    /**
     * Исключение - нужно добавить объект в инжектируемый объет, пришлось сделать так
     * @return
     */
    public CoursesPage getCoursesPage() {
        return coursesPage;
    }
}
