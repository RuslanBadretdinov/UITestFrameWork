package ru.otus.contents.pages.dynamics;

import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.exceptions.UrlIsNeededParametersException;

@UrlPrefix("/lessons/{prefixName}/")
@PageValidation("template:xpath://h1[text()='%s']")
public class CourseItemPage extends AnyPageWithDynamicAnnotationAbs<CourseItemPage> {

    public CourseItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CourseItemPage open() {
        throw new UrlIsNeededParametersException("{prefixName}. Use method - open(prefixName)");
    }

    public CourseItemPage open(String prefixName) {
        driver.get((getBaseUrl() + getUrlPrefix().replace("{prefixName}", prefixName)));
        return this;
    }
}