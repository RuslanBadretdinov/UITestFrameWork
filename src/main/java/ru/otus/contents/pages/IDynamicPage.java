package ru.otus.contents.pages;

public interface IDynamicPage<T> {
    T isLoaded(String titleOfItemPage);
    T setTitleOfItemPage(String titleOfItemPage);
}
