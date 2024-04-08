package ru.otus.contents.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;

@PageValidation("template:xpath://div[text()='%s']")
public class InstructorPage extends AnyPageWithDynamicAnnotationAbs<InstructorPage> {

    @Inject
    public InstructorPage(WebDriver driver) {
        super(driver);
    }
}
