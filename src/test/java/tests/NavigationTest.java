package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Feature("Навигация по приложению")
public class NavigationTest extends BaseTest {

    @Test
    @Story("Навигация по конструктору")
    @Description("Переход по всем вкладкам конструктора: Булки -> Соусы -> Начинки -> Булки")
    public void testConstructorTabsNavigation() {
        // Act & Assert - проверяем переход по вкладкам
        navigateThroughAllConstructorTabs();
    }

    @Test
    @Story("Навигация через элементы интерфейса")
    @Description("Переход на главную страницу через клик на логотип")
    public void testNavigateToMainViaLogo() {
        // Сначала переходим на страницу логина
        navigateToLoginPage();
        waitForPageLoad();

        // Act - кликаем на логотип
        clickLogo();
        waitForPageLoad();

        // Assert - должны быть на главной странице
        assertOnMainPage();
    }

    @Step("Навигация по всем вкладкам конструктора")
    private void navigateThroughAllConstructorTabs() {
        try {
            // Переходим к соусам
            navigateToSaucesSection();
            assertSaucesSectionActive();

            // Переходим к начинкам
            navigateToFillingsSection();
            assertFillingsSectionActive();

            // Возвращаемся к булкам
            navigateToBunsSection();
            assertBunsSectionActive();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Переход к разделу 'Соусы'")
    private void navigateToSaucesSection() throws InterruptedException {
        mainPage.clickSaucesSection();
        Thread.sleep(1000);
    }

    @Step("Переход к разделу 'Начинки'")
    private void navigateToFillingsSection() throws InterruptedException {
        mainPage.clickFillingsSection();
        Thread.sleep(1000);
    }

    @Step("Переход к разделу 'Булки'")
    private void navigateToBunsSection() throws InterruptedException {
        mainPage.clickBunsSection();
        Thread.sleep(1000);
    }

    @Step("Переход на страницу логина")
    private void navigateToLoginPage() {
        mainPage.clickLoginAccountButton();
    }

    @Step("Клик на логотип")
    private void clickLogo() {
        mainPage.clickLogo();
    }

    @Step("Ожидание загрузки страницы")
    private void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверка что раздел 'Соусы' активен")
    private void assertSaucesSectionActive() {
        assertTrue("Должна быть активна вкладка 'Соусы'", mainPage.isSaucesSectionActive());
    }

    @Step("Проверка что раздел 'Начинки' активен")
    private void assertFillingsSectionActive() {
        assertTrue("Должна быть активна вкладка 'Начинки'", mainPage.isFillingsSectionActive());
    }

    @Step("Проверка что раздел 'Булки' активен")
    private void assertBunsSectionActive() {
        assertTrue("Должна быть активна вкладка 'Булки'", mainPage.isBunsSectionActive());
    }

    @Step("Проверка что находимся на главной странице")
    private void assertOnMainPage() {
        assertTrue("Должны быть на главной странице", isOnMainPage());
    }
}