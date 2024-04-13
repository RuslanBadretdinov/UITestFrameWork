package ru.otus.main;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.contents.components.mainpage.NavMenuComponent;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.CoursesDynamicVersionPage;
import ru.otus.extensions.UIExtension;

@ExtendWith(UIExtension.class)
@Tag("@homeWork1")
@DisplayName("Набор тестов - взаимодействие из главной страницей")
public class MainPageTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private NavMenuComponent navMenuComponent;
    @Inject
    private CoursesPage coursesPage;
    @Inject
    private CoursesDynamicVersionPage coursesDynamicVersionPage;

    @Test
    @Tag("@scenario3")
    @DisplayName("Выбор случайной категории курсов из меню 'Обучение' с переходом на страницу 'Все курсы' с включённым фильтром 'Направление' выбранной категории")
    public void openDefiniteCoursePage() {
        mainPage.open().isLoaded();

        String name = navMenuComponent
                .chooseNeededBlockAndSetItemList("Обучение")
                .resetItemListViaLinkContainsPath("https://otus.ru/categories")
                .getRandomItemName();

        navMenuComponent.clickItemByName(name);

        coursesDynamicVersionPage.setTitleOfItemPage(name)
                .isLoaded()
                // С этим DI я уже устал, многие вещи, которые, я думал, что будут работать, не работают.
                // Пример перехода с "динамической" страницы на базовую страницу CoursesPage, в которой весь функционал
                .getCoursesPage()
                .isLoaded();
        // or
        coursesDynamicVersionPage.isLoaded(name);
    }
}
