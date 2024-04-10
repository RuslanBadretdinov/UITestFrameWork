package ru.otus.courses;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.dynamics.CourseItemPage;
import ru.otus.extensions.UIExtension;

@ExtendWith(UIExtension.class)
@Tag("@coursesPageTests")
@DisplayName("Набор тестов - взаимодействие из каталога курсов")
public class CoursesPageTest {

    @Inject
    private CoursesPage coursesPage;

    @Inject
    private CourseItemPage courseItemPage;

    @Test
    @Tag("@scenario1")
    @DisplayName("Открытие страницы конкретного курса")
    public void openDefiniteCoursePage() {
        String courseName = "Reinforcement Learning";

        coursesPage.open()
                .isLoaded()
                .clickNeededCourse(courseName);

        courseItemPage.isLoaded(courseName);
    }

    @Test
    @Tag("@scenario2")
    @DisplayName("Поиск мин и макс даты начала курсов на странице 'Все курсы' " +
            "и проверка названия и даты начала на странице этого курса")
    public void filterCoursesWithDateAndChec() {
        coursesPage.open()
                .isLoaded()

                .setTheEarliestCoursesList()
                .checkDataOfCoursesListWithDataOfCoursePageViaJSOUP()

                .setTheLatestCoursesList()
                .checkDataOfCoursesListWithDataOfCoursePageViaJSOUP();
    }
}