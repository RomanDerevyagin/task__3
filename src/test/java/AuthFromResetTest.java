import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthFromResetTest extends BaseTest {

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
        clickMainButtonAuth(hp);
        clickResetButton(ap);
        clickLoginButton(ap);
        logAuth(ap, email, password);
        checkOrderButton(hp);
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной")
    void clickMainButtonAuth(HomePage hp) {
        hp.clickMainButtonAuth();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    void clickResetButton(AuthPage ap) {
        ap.clickResetButton();
    }

    @Step("Клик по ссылке 'Войти'")
    void clickLoginButton(AuthPage ap) {
        ap.clickLoginButton();
    }

    @Step("Авторизация")
    void logAuth(AuthPage ap, String email, String password) {
        ap.logAuth(email, password);
    }

    @Step("Проверка: появилась кнопка 'Оформить заказ'")
    void checkOrderButton(HomePage hp) {
        String screen = hp.getOrderButtonText();
        assertTrue(screen.contains("Оформить заказ"));
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