import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);

            case "yandex":
                // Указываем путь к YandexDriver
                System.setProperty("webdriver.chrome.driver",
                        "/Users/roman/WebDrivers/yandexdriver");

                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                yandexOptions.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

                return new ChromeDriver(yandexOptions);

            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }
    }
}