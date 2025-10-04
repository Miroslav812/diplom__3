package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Feature("Выход из системы")
public class LogoutTest extends BaseTest {

    @Test
    @Story("Доступ к личному кабинету")
    @Description("Переход в личный кабинет без авторизации должен перенаправлять на страницу логина")
    public void testNavigateToPersonalAccountWithoutLogin() {
        // Act - переходим в личный кабинет
        clickPersonalAccountLink();
        waitForRedirect();

        // Assert - должны быть перенаправлены на логин
        assertRedirectedToLoginPage();
    }

    @Step("Клик на кнопку 'Личный кабинет' без авторизации")
    private void clickPersonalAccountLink() {
        mainPage.clickPersonalAccountLink();
    }

    @Step("Ожидание перенаправления на страницу логина")
    private void waitForRedirect() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверка перенаправления на страницу логина")
    private void assertRedirectedToLoginPage() {
        assertTrue("Должны быть на странице логина", isOnLoginPage());
    }
}