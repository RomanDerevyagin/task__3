import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickIngredientsTest extends BaseTest {

    @BeforeEach
    @Step("Запуск браузера")
    void browser() {
        browserStart();
    }

    @Test
    public void authTest() {
        HomePage hp = new HomePage(driver);
        clickSous(hp);
        checkSousTitle(hp);
        clickNachinki(hp);
        checkNachinkiTitle(hp);
        clickBulki(hp);
        checkBulkiTitle(hp);
    }

    @Step("Клик по вкладке 'Соусы'")
    void clickSous(HomePage hp) {
        hp.clickSous();
    }

    @Step("Клик по вкладке 'Начинки'")
    void clickNachinki(HomePage hp) {
        hp.clickNachinki();
    }

    @Step("Клик по вкладке 'Булки'")
    void clickBulki(HomePage hp) {
        hp.clickBulki();
    }

    @Step("Проверка: отображается заголовок 'Соусы'")
    void checkSousTitle(HomePage hp) {
        assertTrue(hp.getSousText().contains("Соусы"));
    }

    @Step("Проверка: отображается заголовок 'Начинки'")
    void checkNachinkiTitle(HomePage hp) {
        assertTrue(hp.getNachinkiText().contains("Начинки"));
    }

    @Step("Проверка: отображается заголовок 'Булки'")
    void checkBulkiTitle(HomePage hp) {
        assertTrue(hp.getBulkiText().contains("Булки"));
    }

    @AfterEach
    @Step("Закрытие браузера")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}