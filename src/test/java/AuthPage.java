import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    private WebDriver driver;

    private By email = By.xpath("//label[text()='Email']/following-sibling::input");
    private By password = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private By name = By.xpath("//label[text()='Имя']/following-sibling::input");
    private By loginButton = By.className("button_button__33qZ0");
    private By registrButton = By.className("Auth_link__1fOlj");
    private By registrButton1 = By.xpath("//button[text()='Зарегистрироваться']");
    private By loginButton1 = By.className("Auth_link__1fOlj");
    private By resetButton = By.xpath("//a[text()='Восстановить пароль']");
    private By enter = By.xpath("//*[contains(text(),'Вход')]");
    private By exitButton = By.xpath("//button[text()='Выход']");

    public AuthPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickRegistrButton(){
        driver.findElement(registrButton).click();
    }

    public void clickLoginButton(){
        driver.findElement(loginButton1).click();
    }

    public void clickResetButton(){
        driver.findElement(resetButton).click();
    }

    public String getEnterText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement enterElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(enter)
        );
        return enterElement.getText();
    }

    public String pas() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[text()='Некорректный пароль']")
                )
        );
        return errorElement.getText();
    }

    public void clickExitButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(exitButton));
        btn.sendKeys(Keys.ENTER);
    }
    public void logAuth (String email1, String password1){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(email1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(password1);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("/login")
        ));
    }
    public void registrAuth(String name1, String email1 , String password1){
        driver.findElement(name).click();
        driver.findElement(name).sendKeys(name1);
        driver.findElement(email).click();
        driver.findElement(email).sendKeys(email1);
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(password1);
        driver.findElement(registrButton1).click();
    }
}