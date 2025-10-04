package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Feature("Регистрация и восстановление пароля")
public class RegistrationTest extends BaseTest {

    @Test
    @Story("Навигация к формам регистрации")
    @Description("Переход со страницы логина на страницу регистрации")
    public void testNavigateToRegistrationPage() {
        // Act - переходим на страницу регистрации
        navigateToLoginPage();
        waitForPageLoad();

        clickRegisterLink();
        waitForPageLoad();

        // Assert - проверяем, что мы на странице регистрации
        assertOnRegistrationPage();
    }

    @Test
    @Story("Навигация к формам восстановления пароля")
    @Description("Переход со страницы логина на страницу восстановления пароля")
    public void testNavigateToPasswordRecoveryPage() {
        // Act - переходим на страницу восстановления пароля
        navigateToLoginPage();
        waitForPageLoad();

        clickForgotPasswordLink();
        waitForPageLoad();

        // Assert - проверяем, что мы на странице восстановления пароля
        assertOnPasswordRecoveryPage();
    }

    @Step("Переход на страницу логина")
    private void navigateToLoginPage() {
        mainPage.clickLoginAccountButton();
    }

    @Step("Клик на ссылку 'Зарегистрироваться'")
    private void clickRegisterLink() {
        loginPage.clickRegisterLink();
    }

    @Step("Клик на ссылку 'Восстановить пароль'")
    private void clickForgotPasswordLink() {
        loginPage.clickForgotPasswordLink();
    }

    @Step("Ожидание загрузки страницы")
    private void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверка что находимся на странице регистрации")
    private void assertOnRegistrationPage() {
        assertTrue("Должны быть на странице регистрации",
                driver.getCurrentUrl().contains("/register"));
    }

    @Step("Проверка что находимся на странице восстановления пароля")
    private void assertOnPasswordRecoveryPage() {
        assertTrue("Должны быть на странице восстановления пароля",
                driver.getCurrentUrl().contains("/forgot-password"));
    }
}