package ru.otus.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import ru.otus.contents.pages.common.CoursesPage;
import ru.otus.scenario_scoped.GuiceScenarioScoped;

public class CoursesPageSteps {

    @Inject
    private GuiceScenarioScoped guiceScenarioScoped;
    @Inject
    private CoursesPage coursesPage;

    @Когда("^на странице каталога курсов клик по курсу '(.*)'$")
    public void clickItemByName(String itemName) {
        itemName = guiceScenarioScoped.getVariablesUtil().evalText(itemName);
        coursesPage.clickNeededCourse(itemName);
    }

    @Когда("^на странице каталога курсов сверяется список самых '(ранних|поздних)' курсов в страницах этих курсов с помощью JSOP$")
    public void checkEarliestOrLatestDataOfCoursesListWithDataOfCoursePageViaJSOUP(String earliestOrLatest) {
        switch (earliestOrLatest) {
            case "ранних": {
                coursesPage.setTheEarliestCoursesList();
                break;
            }
            case "поздних": {
                coursesPage.setTheLatestCoursesList();
                break;
            }
        }
        coursesPage.checkDataOfCoursesListWithDataOfCoursePageViaJSOUP();
    }
}