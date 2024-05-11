package ru.otus.contents.pages.dynamics;

import com.google.inject.Inject;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageWithDynamicAnnotationAbs;
import ru.otus.exceptions.UrlIsNeededParametersException;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

@UrlPrefix("/instructors/{id}")
@PageValidation("template:xpath://div[text()='%s']")
public class InstructorItemPage extends AnyPageWithDynamicAnnotationAbs<InstructorItemPage> {

    @Inject
    public InstructorItemPage(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

    public InstructorItemPage open() {
        throw new UrlIsNeededParametersException("{id}. Use method - open(id)");
    }

    public InstructorItemPage open(String id) {
        this.driver.get((getBaseUrl() + getUrlPrefix().replace("{id}", id)));
        return this;
    }
}
