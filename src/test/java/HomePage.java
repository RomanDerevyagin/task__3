import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // ← добавить
    }
    private By mainButtonAuth = By.className("button_button__33qZ0");
    public void clickMainButtonAuth(){
        driver.findElement(mainButtonAuth).click();
    }
    private By orderButton = By.xpath("//button[text()='Оформить заказ']");
    public String getOrderButtonText(){
        return driver.findElement(orderButton).getText();
    }
    private By personalAccountButton = By.xpath(
            "//p[contains(@class,'AppHeader_header__linkText') and text()='Личный Кабинет']/.."
    );
    private By constructorButton = By.xpath("//p[text()='Конструктор']");
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    private By exitButton = By.className("Account_button__14Yp3");
    private By profile = By.xpath("//a[text()='Профиль']");
    private By mainPageTitle = By.xpath("//h1[text()='Соберите бургер']");
    public String getMainPageText(){
        return driver.findElement(mainPageTitle).getText();
    }
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
    public void goToProfileDirectly() {
        driver.get("https://stellarburgers.education-services.ru/account/profile");
    }
    public void clickLogo(){
        driver.findElement(logo).click();
    }
    private By sousTab = By.xpath("//span[text()='Соусы']");
    private By nachinkiTab = By.xpath("//span[text()='Начинки']");
    private By bulkiTab = By.xpath("//span[text()='Булки']");
    private By sousTitle = By.xpath("//h2[text()='Соусы']");
    private By bulkiTitle = By.xpath("//h2[text()='Булки']");
    private By nachinkiTitle = By.xpath("//h2[text()='Начинки']");
    public String getSousText(){
        return driver.findElement(sousTitle).getText();
    }
    public String getNachinkiText(){
        return driver.findElement(nachinkiTitle).getText();
    }
    public String getBulkiText(){
        return driver.findElement(bulkiTitle).getText();
    }
    public void clickBulki(){
        driver.findElement(bulkiTab).click();
    }
    public void clickSous(){
        driver.findElement(sousTab).click();
    }
    public void clickNachinki(){
        driver.findElement(nachinkiTab).click();
    }


}