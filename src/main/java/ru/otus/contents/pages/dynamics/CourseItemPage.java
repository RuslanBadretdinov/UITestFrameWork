package ru.otus.contents.pages.dynamics;

import com.google.inject.Inject;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.exceptions.UrlIsNeededParametersException;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

@UrlPrefix("/lessons/{prefixName}/")
@PageValidation("template:xpath://h1[text()='%s']")
public class CourseItemPage extends AnyPageWithDynamicAnnotationAbs<CourseItemPage> {

    @Inject
    public CourseItemPage(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

    @Override
    public CourseItemPage open() {
        throw new UrlIsNeededParametersException("{prefixName}. Use method - open(prefixName)");
    }

    public CourseItemPage open(String prefixName) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{prefixName}", prefixName)));
        return this;
    }
}