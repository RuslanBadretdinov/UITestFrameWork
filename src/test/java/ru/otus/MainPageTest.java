package ru.otus;

import ru.otus.annotations.Driver;
import ru.otus.extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UIExtension.class)
public class MainPageTest {

    @Driver
    private WebDriver driver;

    @Test
    public void loadMainPage() {

    }
}
