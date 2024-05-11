package ru.otus.contents.pages.dynamics;

import com.google.inject.Inject;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.PageLibrary;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

@UrlPrefix("/catalog/courses?categories={category}")
@PageValidation("template:xpath://div[@class = 'ReactCollapse--content']//div[@value = 'true']//label[text() = '%s']")
public class CoursesDynamicVersionPage extends AnyPageWithDynamicAnnotationAbs<CoursesDynamicVersionPage> {

    @Inject
    public CoursesDynamicVersionPage(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

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
        return PageLibrary.getInstance().getCoursesPage();
    }

    /**
     * Напоролся на ошибки, понял, что лучше сделать так
     * @return
     */
    public CoursesPage getCoursesPage() {
        return PageLibrary.getInstance().getCoursesPage();
    }
}
