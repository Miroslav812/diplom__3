package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Feature("Авторизация пользователя")
public class LoginTest extends BaseTest {

    @Test
    @Story("Способы входа в систему")
    @Description("Вход через кнопку 'Войти в аккаунт' на главной странице")
    public void testLoginViaMainPageLoginButton() {
        // Act - кликаем на кнопку входа
        clickLoginAccountButton();
        waitForPageLoad();

        // Assert - проверяем, что мы на странице логина
        assertOnLoginPage();
    }

    @Test
    @Story("Способы входа в систему")
    @Description("Вход через кнопку 'Личный кабинет' на главной странице")
    public void testLoginViaPersonalAccountButton() {
        // Act - кликаем на личный кабинет
        clickPersonalAccountLink();
        waitForPageLoad();

        // Assert - проверяем, что мы на странице логина
        assertOnLoginPage();
    }

    @Step("Клик на кнопку 'Войти в аккаунт'")
    private void clickLoginAccountButton() {
        mainPage.clickLoginAccountButton();
    }

    @Step("Клик на кнопку 'Личный кабинет'")
    private void clickPersonalAccountLink() {
        mainPage.clickPersonalAccountLink();
    }

    @Step("Ожидание загрузки страницы")
    private void waitForPageLoad() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверка что находимся на странице логина")
    private void assertOnLoginPage() {
        assertTrue("Должны быть на странице логина", isOnLoginPage());
    }
}