package ru.otus.contents.components;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IComponent<T> {
    T chooseNeededBlockAndMoveToThis(String blockName);
    T setItemList();
    T chooseNeededBlockAndSetItemList(String blockName);
    List<WebElement> getItemList();
    void clickItemByName(String blockName, String name);
}
