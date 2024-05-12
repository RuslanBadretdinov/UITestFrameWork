package ru.otus.contents.components;

import ru.otus.contents.components.abstracts.AnyComponentAbs;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class ComponentLibrary {

    private Map<String, IComponent<? extends AnyComponentAbs<?>>> iComponentMap;

    public ComponentLibrary(Map<String, IComponent<? extends AnyComponentAbs<?>>> iComponentMap) {
        this.iComponentMap = iComponentMap;
    }

    public IComponent<? extends AnyComponentAbs<?>> getComponent(String pageName) {
        if (iComponentMap.containsKey(pageName)) {
            return iComponentMap.get(pageName);
        }
        String failMessage = String.format("страница '%s' не опознана из feature файла", pageName);
        fail(failMessage);
        throw new IllegalArgumentException(failMessage);
    }

    public IComponent<? extends AnyComponentAbs<?>> getComponent(Class<?> clazz) {
        for(Map.Entry<String, IComponent<? extends AnyComponentAbs<?>>> entry: iComponentMap.entrySet()) {
            if (entry.getValue().getClass() == clazz) {
                return entry.getValue();
            }
        }
        String failMessage = String.format("страница по имени класса '%s' не опознана", clazz.getSimpleName());
        fail(failMessage);
        throw new IllegalArgumentException(failMessage);
    }
}