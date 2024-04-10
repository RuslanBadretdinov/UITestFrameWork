package ru.otus.main;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.CoursesDynamicVersionPage;
import ru.otus.extensions.UIExtension;

@ExtendWith(UIExtension.class)
@Tag("@mainPageTests")
@DisplayName("Набор тестов - взаимодействие из главной страницей")
public class MainPageTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private CoursesPage coursesPage;
    @Inject
    private CoursesDynamicVersionPage coursesDynamicVersionPage;

    @Test
    @Tag("@scenario3")
    @DisplayName("Выбор случайной категории курсов из меню 'Обучение' с переходом на страницу 'Все курсы' " +
            "с включённым фильтром 'Направление' выбранной категории")
    public void openDefiniteCoursePage() {
        mainPage.open().isLoaded();

        System.out.println(1);

//        coursesPage.open()
//                .isLoaded()
//                .clickNeededCourse(courseName);
//
//        courseItemPage.isLoaded(courseName);
    }
}
