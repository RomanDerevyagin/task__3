import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitButtonTest extends BaseTest {

    @BeforeEach
    @Step("Запуск браузера")
    void browser() {
        browserStart();
    }

    @ParameterizedTest
    @MethodSource("order")
    public void authTest(String email, String password) {
        AuthPage ap = new AuthPage(driver);
        HomePage hp = new HomePage(driver);
        clickPersonalAccountButton(hp);
        logAuth(ap, email, password);
        goToProfileDirectly(hp);
        clickExitButton(ap);
        checkLoginPage(ap);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    void clickPersonalAccountButton(HomePage hp) {
        hp.clickPersonalAccountButton();
    }

    @Step("Авторизация")
    void logAuth(AuthPage ap, String email, String password) {
        ap.logAuth(email, password);
    }
    @Step("Переход в личный кабинет")
    void goToProfileDirectly(HomePage hp){
        hp.goToProfileDirectly();
    }

    @Step("Клик по кнопке 'Выход'")
    void clickExitButton(AuthPage ap) {
        ap.clickExitButton();
    }

    @Step("Проверка: вернулись на страницу входа")
    void checkLoginPage(AuthPage ap) {
        String screen = ap.getEnterText();
        assertTrue(screen.contains("Вход"));
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    static Stream<Arguments> order() {
        return Stream.of(Arguments.of("change@test.ru", "12345678"));
    }
}