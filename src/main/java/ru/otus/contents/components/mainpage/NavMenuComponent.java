package ru.otus.contents.components.mainpage;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.Component;
import ru.otus.contents.components.abstracts.AnyComponentAbs;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

@Component("xpath://nav/div[span[text() = '%s']]")
public class NavMenuComponent extends AnyComponentAbs<NavMenuComponent> {

    @Inject
    public NavMenuComponent(WebDriver driver) { super(driver); }

    @Override
    public NavMenuComponent setItemList() {
        this.itemList = getComponentEntity(this.incomingValueForAnnotation)
                .findElements(By.xpath("/following-sibling::div[1]//a"));
        return this;
    }

    public NavMenuComponent resetItemListViaLinkContainsPath(String linkContainsPath) {
        this.itemList = this.itemList.stream()
                .filter(el-> el.getAttribute("href").contains(linkContainsPath))
                .collect(Collectors.toList());
        return this;
    }

    public String getRandomItemName() {
        return new Faker().options().nextElement(this.itemList).getText().replaceAll("\\(\\d\\)", "").trim();
    }
}
