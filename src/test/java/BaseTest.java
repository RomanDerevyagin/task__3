import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;
    protected void browserStart() {
        String browser = System.getProperty("browser", "chrome");
        Allure.addAttachment("Browser", browser);
        driver = WebDriverFactory.getDriver(browser);
        driver.get("https://stellarburgers.education-services.ru");
    }
}