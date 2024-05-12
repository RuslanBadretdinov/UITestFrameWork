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

    private Map<String, IPage<? extends AnyPageAbs<?>>> pageMap = new HashMap<>() {{
        put("Главная страница", mainPage);
    }};

    public Map<String, IPage<? extends AnyPageAbs<?>>> getPageMap() {
        return pageMap;
    }

    /**
     * Исключение - нужно добавить объект в инжектируемый объет, пришлось сделать так
     * @return
     */
    public CoursesPage getCoursesPage() {
        return coursesPage;
    }
}
