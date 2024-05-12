package ru.otus.contents.pages;

public interface IPage<T> {

    T open();
    T isLoaded();
}
