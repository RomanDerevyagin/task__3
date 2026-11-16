import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickButtonTest extends BaseTest {

    @BeforeEach
    @Step("Запуск браузера")
    void browser() {
        browserStart();
    }

    @Test
    public void authTest() {
        AuthPage ap = new AuthPage(driver);
        HomePage hp = new HomePage(driver);
        clickPersonalAccountButton(hp);
        checkLoginPage(ap);
        clickConstructorButton(hp);
        checkMainPage(hp);
        clickPersonalAccountButton(hp);
        checkLoginPage(ap);
        clickLogo(hp);
        checkMainPage(hp);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    void clickPersonalAccountButton(HomePage hp) {
        hp.clickPersonalAccountButton();
    }

    @Step("Клик по кнопке 'Конструктор'")
    void clickConstructorButton(HomePage hp) {
        hp.clickConstructorButton();
    }

    @Step("Клик по логотипу")
    void clickLogo(HomePage hp) {
        hp.clickLogo();
    }

    @Step("Проверка: открылась страница входа")
    void checkLoginPage(AuthPage ap) {
        assertTrue(ap.getEnterText().contains("Вход"));
    }

    @Step("Проверка: открылась главная страница")
    void checkMainPage(HomePage hp) {
        assertTrue(hp.getMainPageText().contains("Соберите бургер"));
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}