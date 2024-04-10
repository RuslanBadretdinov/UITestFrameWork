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
@Tag("@exampleSetTests")
@DisplayName("Набор тестов со страницей преподавателя")
public class CoursesPageTest {

    @Inject
    private CoursesPage coursesPage;

    @Inject
    private CourseItemPage courseItemPage;

    @Test
    @Tag("@scenario1a")
    @DisplayName("Открытие страницы конкретного курса")
    public void openDefiniteCoursePage() {
        String courseName = "Reinforcement Learning";

        coursesPage.open()
                .isLoaded()
                .setCoursesList()
                .clickNeededCourse(courseName);

        courseItemPage.isLoaded(courseName);
    }
}