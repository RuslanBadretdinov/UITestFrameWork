package ru.otus.contents.pages;

import ru.otus.contents.pages.abstracts.AnyPageAbs;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class PageLibrary {

    private Map<String, IPage<? extends AnyPageAbs<?>>> iPageMap;

    public PageLibrary(Map<String, IPage<? extends AnyPageAbs<?>>> iPageMap) {
        this.iPageMap = iPageMap;
    }

    public IPage<? extends AnyPageAbs<?>> getPage(String pageName) {
        if (iPageMap.containsKey(pageName)) {
            return iPageMap.get(pageName);
        }
        String failMessage = String.format("страница '%s' не опознана из feature файла", pageName);
        fail(failMessage);
        throw new IllegalArgumentException(failMessage);
    }

    /**
     * Исключение - нужно добавить объект в инжектируемый объет, пришлось сделать так
     *
     * @return
     */
    public IPage<? extends AnyPageAbs<?>> getPage(Class<?> clazz) {
        for(Map.Entry<String, IPage<? extends AnyPageAbs<?>>> entry: iPageMap.entrySet()) {
            if (entry.getValue().getClass() == clazz) {
                return entry.getValue();
            }
        }
        String failMessage = String.format("страница по имени класса '%s' не опознана", clazz.getSimpleName());
        fail(failMessage);
        throw new IllegalArgumentException(failMessage);
    }
}
