import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationFailTest extends BaseTest {

    @BeforeEach
    @Step("Запуск браузера")
    void browser() {
        browserStart();
    }

    @ParameterizedTest
    @MethodSource("order")
    public void authTest(String name, String email, String password) {
        AuthPage ap = new AuthPage(driver);
        HomePage hp = new HomePage(driver);

        clickMainButtonAuth(hp);
        clickRegistrButton(ap);
        registrAuth(ap, name, email, password);
        checkIncorrectPasswordError(ap);
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной")
    void clickMainButtonAuth(HomePage hp) {
        hp.clickMainButtonAuth();
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    void clickRegistrButton(AuthPage ap) {
        ap.clickRegistrButton();
    }

    @Step("Регистрация")
    void registrAuth(AuthPage ap, String name, String email, String password) {
        ap.registrAuth(name, email, password);
    }

    @Step("Проверка: появилась ошибка 'Некорректный пароль'")
    void checkIncorrectPasswordError(AuthPage ap) {
        assertTrue(ap.pas().contains("Некорректный пароль"));
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    static Stream<Arguments> order() {
        return Stream.of(Arguments.of("lol", "lol@test.ru", "12345"));
    }
}