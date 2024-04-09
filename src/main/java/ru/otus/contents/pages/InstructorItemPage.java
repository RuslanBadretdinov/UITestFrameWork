package ru.otus.contents.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.exceptions.UrlIsNeededParametersException;

@UrlPrefix("/instructors/{id}")
@PageValidation("template:xpath://div[text()='%s']")
public class InstructorItemPage extends AnyPageWithDynamicAnnotationAbs<InstructorItemPage> {

    @Inject
    public InstructorItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public InstructorItemPage open() {
        throw new UrlIsNeededParametersException("{id}. Use method - open(id)");
    }

    public InstructorItemPage open(String id) {
        driver.get((getBaseUrl() + getUrlPrefix().replace("{id}", id)));
        return this;
    }
}
