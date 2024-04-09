package ru.otus.contents.pages.common;


import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.annotations.UrlPrefix;
import ru.otus.contents.pages.abstracts.AnyPageAbs;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

    @FindBy(xpath = "//main/..//img[@alt='OTUS Logo']")
    private WebElement isLoadedElement;

    @Inject
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage isLoaded() {
        this.isLoaded(this.isLoadedElement);
        return this;
    }
}
