package example.teachers;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.contents.components.mainpage.BlockWithItemsComponent;
import ru.otus.contents.pages.common.MainPage;
import ru.otus.contents.pages.dynamics.InstructorItemPage;
import ru.otus.extensions.UIExtension;

@ExtendWith(UIExtension.class)
@Tag("@homeWork1")
@DisplayName("Набор тестов со страницей преподавателя")
public class TeacherBlockTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private BlockWithItemsComponent blockWithItemsComponent;
    @Inject
    private InstructorItemPage instructorItemPage;

    @Test
    @Tag("@openTeacherCardByClick")
    @DisplayName("Открытие страницы случайного преподавателя")
    public void openTeacherCardByClick() {
        mainPage.open().isLoaded();

        String name = blockWithItemsComponent
                .chooseNeededBlockAndSetItemList("Преподаватели")
                .getItemWithTextParameterAndIndex(1, 1);
        blockWithItemsComponent.clickItemByName(name);

        instructorItemPage.isLoaded(name);
    }
}
