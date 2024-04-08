package ru.otus.example.teachers;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.contents.components.BlockWithItemsComponent;
import ru.otus.contents.pages.InstructorPage;
import ru.otus.extensions.UIExtension;
import ru.otus.contents.pages.MainPage;

@ExtendWith(UIExtension.class)
public class TeacherBlockTest {

    @Inject
    private MainPage mainPage;
    @Inject
    private BlockWithItemsComponent blockWithItemsComponent;
    @Inject
    private InstructorPage instructorsPage;


    @Test
    public void openTeacherCardByClick() {
        mainPage.open();

        String name = blockWithItemsComponent.chooseNeededBlockAndSetItemList("Преподаватели")
                .getItemParameterTextByIndex(1, 1);
        blockWithItemsComponent.clickItemByName(name);

//        instructorsPage.isLoaded(name);

    }
}
