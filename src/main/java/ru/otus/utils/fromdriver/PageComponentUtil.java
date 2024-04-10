package ru.otus.utils.fromdriver;

import org.openqa.selenium.By;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageComponentUtil {
    private static PageComponentUtil INSTANCE;

    private PageComponentUtil() {

    }

    protected static PageComponentUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageComponentUtil();
        }
        return INSTANCE;
    }

    public By defineLocatorTypeByAnnotationValue(String annotationValue) throws IllegalArgumentException {
        String searchStrategy = "";

        Pattern pattern = Pattern.compile("^(\\w+):.*?");
        Matcher matcher = pattern.matcher(annotationValue);
        if (matcher.find()) {
            searchStrategy = matcher.group(1).toLowerCase(Locale.ROOT);
        }

        String resultLocator = annotationValue.replace(String.format("%s:", searchStrategy), "");
        switch (searchStrategy) {
            case "xpath":
                return By.xpath(resultLocator);
            case "id":
                return By.id(resultLocator);
            case "css":
                return By.cssSelector(resultLocator);
        }
        throw new IllegalArgumentException(
                String.format(
                        "Locator type is not defined. Annotation value is '%s'", annotationValue
                )
        );
    }
}
