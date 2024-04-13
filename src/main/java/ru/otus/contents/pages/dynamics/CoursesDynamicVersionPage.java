package ru.otus.contents.pages.dynamics;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.contents.pages.common.CoursesPage;

@UrlPrefix("/catalog/courses?categories={category}")
@PageValidation("template:xpath://div[@class = 'ReactCollapse--content']//div[@value = 'true']//label[text() = '%s']")
public class CoursesDynamicVersionPage extends AnyPageWithDynamicAnnotationAbs<CoursesDynamicVersionPage> {

    @Inject
    private CoursesPage coursesPage;

    public CoursesDynamicVersionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoursesDynamicVersionPage isLoaded() {
        return super.isLoaded(this.incomingValueForAnnotation);
    }

    @Override
    public CoursesDynamicVersionPage isLoaded(String titleOfItemPage) {
        return super.isLoaded(titleOfItemPage);
    }

    public CoursesPage open(String category) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{category}", category)));
        return coursesPage;
    }

    /**
     * Напоролся на ошибки, понял, что лучше сделать так
     * @return
     */
    public CoursesPage getCoursesPage() {
        return coursesPage;
    }
}
