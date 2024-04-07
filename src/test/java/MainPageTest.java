import annotations.Driver;
import extensions.UIReflectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(UIReflectionExtension.class)
public class MainPageTest {

    @Driver
    private WebDriver driver;

    @Test
    public void loadMainPage() {

    }
}
