import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    private String email;
    private String password;

    @BeforeEach
    @Step("Запуск браузера")
    void browser() {
        browserStart();
    }

    @ParameterizedTest
    @MethodSource("order")
    public void authTest(String name, String emailFromParams, String passwordFromParams) {
        this.email = emailFromParams;
        this.password = passwordFromParams;

        AuthPage ap = new AuthPage(driver);
        HomePage hp = new HomePage(driver);

        clickMainButtonAuth(hp);
        clickRegistrButton(ap);
        registrAuth(ap, name, emailFromParams, passwordFromParams);
        checkLoginPage(ap);
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

    @Step("Проверка: после регистрации открылась страница входа")
    void checkLoginPage(AuthPage ap) {
        assertTrue(ap.getEnterText().contains("Вход"));
    }

    @AfterEach
    @Step("Удаление пользователя и закрытие браузера")
    public void teardown() {
        if (email != null) {
            RestAssured.baseURI = "https://stellarburgers.education-services.ru/api";
            Response loginResponse = given()
                    .header("Content-Type", "application/json")
                    .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                    .post("/auth/login");

            String token = loginResponse.jsonPath().getString("accessToken");
            if (token != null) {
                given().header("Authorization", token).delete("/auth/user");
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }

    static Stream<Arguments> order() {
        return Stream.of(Arguments.of("lol", "lolik@test.ru", "12345678"));
    }
}