package ru.otus.contents.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.exceptions.UrlIsNeededParametersException;

@UrlPrefix("/instructors/{id}")
@PageValidation("template:xpath://div[text()='%s']")
public class InstructorPage extends AnyPageWithDynamicAnnotationAbs<InstructorPage> {

    @Inject
    public InstructorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public InstructorPage open() {
        throw new UrlIsNeededParametersException("{id}. Use method - open(id)");
    }

    public InstructorPage open(String id) {
        driver.get((getBaseUrl() + getUrlPrefix().replace("{id}", id)));
        return this;
    }
}
