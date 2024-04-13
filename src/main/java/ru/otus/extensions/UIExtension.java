package ru.otus.extensions;

import com.google.inject.Module;
import com.google.inject.*;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import ru.otus.modules.GuiceContentsModule;
import ru.otus.modules.GuiceDriverModule;
import ru.otus.modules.GuiceUtilsModule;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

    @Inject
    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        GuiceDriverModule guiceDriverModule = new GuiceDriverModule();
        Injector driverInjector = Guice.createInjector(guiceDriverModule);

        driverInjector.injectMembers(this);

        GuiceContentsModule guiceContentsModule = new GuiceContentsModule();
        GuiceUtilsModule guiceUtilsModule = new GuiceUtilsModule();

        this.injectDriverToModules(driverInjector, guiceContentsModule, guiceContentsModule);
        Injector fullInjector = Guice.createInjector(guiceDriverModule, guiceContentsModule, guiceUtilsModule);

        extensionContext.getTestInstance().ifPresent(instance -> {
            fullInjector.injectMembers(instance);
        });
        extensionContext.getTestInstance().ifPresent(instance -> {
            Arrays.stream(instance.getClass().getDeclaredFields())
                    .map(f -> f.getType())
                    .forEach(c -> fullInjector.injectMembers(fullInjector.getProvider(c).get()));
        });
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getTestInstance()
                .ifPresent(instance -> {
                    if (driver != null) {
                        driver.quit();
                    }
                });
    }

    private void injectDriverToModules(Injector driverInjector, Module... modules) {
        for (Module module : modules) {
            driverInjector.injectMembers(module);
        }
    }

    /**
     * Попытка №2 - Считаю, что пригодится для Java Reflection, чтобы подсматривать, поэтому оставляю эти комментари
     * для будущего себя :D
     *
     * @param extensionContext
     * @param fullInjector
     */
    private void extensionContextMyFailVersion(ExtensionContext extensionContext, Injector fullInjector) {
        extensionContext.getTestInstance().ifPresent(instance -> {
            Arrays.stream(instance.getClass().getDeclaredFields())
                    .map(field -> field.getType())
                    .forEach(c -> setInnerContentVariablesSettings(c, fullInjector, Inject.class));
        });
    }

    /**
     * Пытался понять что такое Guice в итоге понял только такие методы, которые вроде как работают:
     * fullInjector.injectMembers(instance); "инжектит (инициализирует в runtime) поля объекта instance"
     * fullInjector.getProvider(instance).get() - выдаёт твой созданный объект (я ставил аннтоации @Singleton)
     * + понял, что если класса в проекте не создано, например объект new Actions() от селениума, то Guice инжектить
     * с помощью метода getAcions() tне будет такой объект нужно создавать до Runtime (В source? (короче, на этапе компиляции))
     * <p>
     * Итог: за 2 дня опытным путём понял, как работают эти 2 метода
     * <p>
     * Попытка 1 . Тестировал DI. Пытался объеденить guice и Java Reflect (+ до этого была боль, сидел, смотрел, тестировал, не спал)
     *
     * @param instance
     * @param injector
     * @param guiceContentsModule
     */
    @Deprecated
    private void setInnerContentVariablesSettings(Object instance, Injector injector, GuiceContentsModule guiceContentsModule) {
        Arrays.stream(instance.getClass().getDeclaredFields())
                .map(field -> field.getType())
                .filter(c ->
                        Arrays.stream(guiceContentsModule.getClass().getMethods())
                                .filter(m -> m.getAnnotation(Provides.class) != null)
                                .map(m -> m.getReturnType())
                                .filter(rt ->
                                        Arrays.stream(rt.getDeclaredFields())
                                                .anyMatch(f -> f.getAnnotation(Inject.class) != null)
                                )
                                .collect(Collectors.toList()).contains(c)
                )
                .map(c -> {
                    Map<Class<?>, List<Class<?>>> injectMap = new HashMap();
                    List<Class<?>> injectFieldsList =
                            Arrays.stream(c.getDeclaredFields())
                                    .filter(f -> f.getAnnotation(Inject.class) != null)
                                    .map(f -> f.getType())
                                    .collect(Collectors.toList());
                    injectMap.put(c, injectFieldsList);
                    return injectMap;
                })
                .forEach(m -> {
                    for (Map.Entry<Class<?>, List<Class<?>>> entry : m.entrySet()) {
                        injector.injectMembers(entry.getKey());
                        Object owner = injector.getProvider(entry.getKey()).get();
                        Arrays.stream(owner.getClass().getDeclaredFields())
                                .filter(field -> entry.getValue().contains(field.getType()))
                                .forEach(
                                        innerField -> {
                                            innerField.setAccessible(true);
                                            try {
                                                innerField.set(owner, injector.getProvider(innerField.getType()).get());
                                                innerField.setAccessible(false);
                                            } catch (IllegalAccessException e) {
                                                e.printStackTrace();
                                            }
                                        });
                    }
                });
    }

    /**
     * Попытка №2 - боль № 2 понял, что есть возможность получать объекты родительского клааса в Java Reflect
     *
     * @param objectClass
     * @param fullInjector
     * @param neededAnnotation
     */
    @Deprecated
    private void setInnerContentVariablesSettings(Class<?> objectClass, Injector fullInjector, Class<? extends Annotation> neededAnnotation) {
        List<Field> neededInjectFieldList = getAllFieldsAndSuperFieldsWithNeededAnnotations(objectClass, neededAnnotation);
        Object owner = fullInjector.getProvider(objectClass).get();
        if (!neededInjectFieldList.isEmpty()) {
            neededInjectFieldList.stream()
                    .forEach(innerField -> {
                        innerField.setAccessible(true);
                        try {
                            innerField.set(owner, fullInjector.getProvider(innerField.getType()).get());
                            innerField.setAccessible(false);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    /**
     * Попытка №2 - "Разделяй и властвуй" :D - Поиск полкй и полей родителя с конкретной аннотацией
     *
     * @param objectClass
     * @param neededAnnotation
     * @return
     */
    @Deprecated
    private List<Field> getAllFieldsAndSuperFieldsWithNeededAnnotations(Class<?> objectClass, Class<? extends Annotation> neededAnnotation) {
        List<Field> fullFieldClasses = new ArrayList<>();
        Class<?> ob = objectClass;
        while (ob != null) {
            fullFieldClasses.addAll(
                    Arrays.stream(ob.getDeclaredFields())
                            .filter(f -> f.getAnnotation((Class<? extends Annotation>) neededAnnotation) != null)
                            .collect(Collectors.toList())
            );
            ob = ob.getSuperclass();
        }
        return fullFieldClasses;
    }
}
