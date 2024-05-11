package ru.otus.contents.components.abstracts;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.Component;
import ru.otus.scenario_scoped.GuiceScenarioScoped;
import ru.otus.utils.fromdriver.CommonActions;
import ru.otus.utils.fromdriver.PageComponentUtil;

import java.util.List;

public abstract class AnyComponentAbs<T> extends CommonActions<T> {
    protected String incomingValueForAnnotation;
    protected List<WebElement> itemList;

    @Inject
    public AnyComponentAbs(GuiceScenarioScoped guiceScenarioScoped) { super(guiceScenarioScoped); }

    protected WebElement getComponentEntity(String incomingValueForAnnotation) {
        this.addValueForAnnotation(incomingValueForAnnotation);
        return $(getComponentLocator());
    }

    protected By getComponentLocator() {
        Component component = getClass().getAnnotation(Component.class);

        if (component != null) {
            String value = component.value();

            if (this.incomingValueForAnnotation.isEmpty()) return null;

            value = String.format(value, this.incomingValueForAnnotation);
            return PageComponentUtil.defineLocatorTypeByAnnotationValue(value);
        }
        return null;
    }

    public T chooseNeededBlockAndMoveToThis(String blockName) {
        this.actions.moveToElement(getComponentEntity(blockName)).build().perform();
        return (T) this;
    }

    public abstract T setItemList();


    public T chooseNeededBlockAndSetItemList(String blockName) {
        chooseNeededBlockAndMoveToThis(blockName);
        setItemList();
        return (T) this;
    }

    public List<WebElement> getItemList() {
        return itemList;
    }

    protected WebElement getItemWebElement(int index) {
        return itemList.get(--index);
    }

    protected WebElement getItemParameterWebElementByIndex(WebElement item, String xpathValue, int indexParameter) {
        return item.findElement(By.xpath(String.format(xpathValue + "[%d]", indexParameter)));
    }

    private void addValueForAnnotation(String incomingValueForAnnotation) {
        this.incomingValueForAnnotation = incomingValueForAnnotation;
    }
}
