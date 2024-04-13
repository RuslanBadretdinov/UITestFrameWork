package ru.otus.contents.pages.dynamics;

import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.exceptions.UrlIsNeededParametersException;

@UrlPrefix("/instructors/{id}")
@PageValidation("template:xpath://div[text()='%s']")
public class InstructorItemPage extends AnyPageWithDynamicAnnotationAbs<InstructorItemPage> {

    public InstructorItemPage(WebDriver driver) {
        super(driver);
    }

    public InstructorItemPage open() {
        throw new UrlIsNeededParametersException("{id}. Use method - open(id)");
    }

    public InstructorItemPage open(String id) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{id}", id)));
        return this;
    }
}
