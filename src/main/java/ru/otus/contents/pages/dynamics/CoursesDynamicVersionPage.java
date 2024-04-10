package ru.otus.contents.pages.dynamics;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.contents.pages.common.CoursesPage;

@UrlPrefix("/catalog/courses?categories={category}")
@PageValidation("template:xpath://div[@value = 'true']//label[text() = '%s']")
public class CoursesDynamicVersionPage extends AnyPageWithDynamicAnnotationAbs<CoursesPage> {

    @Inject
    private CoursesPage coursesPage;

    @Inject
    public CoursesDynamicVersionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoursesPage isLoaded() {
        return coursesPage.isLoaded();
    }

    @Override
    public CoursesPage isLoaded(String titleOfItemPage) {
        this.isLoaded();
        return super.isLoaded(titleOfItemPage);
    }

    public CoursesPage open(String category) {
        driver.get((getBaseUrl() + getUrlPrefix().replace("{category}", category)));
        return coursesPage;
    }
}
