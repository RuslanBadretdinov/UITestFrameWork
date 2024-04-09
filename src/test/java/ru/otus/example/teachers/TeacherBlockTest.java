package ru.otus.example.teachers;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.contents.components.BlockWithItemsComponent;
import ru.otus.contents.pages.InstructorPage;
import ru.otus.contents.pages.MainPage;
import ru.otus.extensions.UIExtension;

@ExtendWith(UIExtension.class)
@Tag("@exampleSetTests")
@DisplayName("Набор тестов со страницей преподавателя")
public class TeacherBlockTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private BlockWithItemsComponent blockWithItemsComponent;
    @Inject
    private InstructorPage instructorsPage;

    @Test
    @Tag("@openRandomTeacherPage")
    @DisplayName("Открытие страницы случайного преподавателя")
    public void openTeacherCardByClick() {
        mainPage.open();

        String name = blockWithItemsComponent.
                chooseNeededBlockAndSetItemList("Преподаватели")
                .getItemWithTextParameterAndIndex(1, 1);
        blockWithItemsComponent.clickItemByName(name);

        instructorsPage.isLoaded(name);
        System.out.println("1");

    }
}
