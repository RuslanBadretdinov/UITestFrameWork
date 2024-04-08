package ru.otus.contents.pages;


import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.UrlPrefix;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

    @Inject
    public MainPage(WebDriver driver) {
        super(driver);
    }
}
