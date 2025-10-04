package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@Feature("Конструктор бургеров")
public class ConstructorTest extends BaseTest {

    @Test
    @Story("Навигация по разделам конструктора")
    @Description("Переход к разделу 'Булки' через другие разделы")
    public void testNavigateToBunsSection() {
        // Сначала переходим к другому разделу, затем возвращаемся к булкам
        try {
            // Переходим к соусам
            navigateToSaucesSection();

            // Теперь переходим к булкам
            navigateToBunsSection();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Assert
        assertBunsSectionActive();
    }

    @Test
    @Story("Навигация по разделам конструктора")
    @Description("Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        // Act
        try {
            navigateToSaucesSection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Assert
        assertSaucesSectionActive();
    }

    @Test
    @Story("Навигация по разделам конструктора")
    @Description("Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        // Act
        try {
            navigateToFillingsSection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Assert
        assertFillingsSectionActive();
    }

    @Test
    @Story("Навигация по разделам конструктора")
    @Description("Последовательная навигация по всем разделам конструктора")
    public void testAllConstructorSectionsNavigation() {
        try {
            // 1. Переходим к соусам
            navigateToSaucesSection();
            assertSaucesSectionActive();

            // 2. Переходим к начинкам
            navigateToFillingsSection();
            assertFillingsSectionActive();

            // 3. Возвращаемся к булкам
            navigateToBunsSection();
            assertBunsSectionActive();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Переход к разделу 'Булки'")
    private void navigateToBunsSection() throws InterruptedException {
        mainPage.clickBunsSection();
        Thread.sleep(2000);
    }

    @Step("Переход к разделу 'Соусы'")
    private void navigateToSaucesSection() throws InterruptedException {
        mainPage.clickSaucesSection();
        Thread.sleep(2000);
    }

    @Step("Переход к разделу 'Начинки'")
    private void navigateToFillingsSection() throws InterruptedException {
        mainPage.clickFillingsSection();
        Thread.sleep(2000);
    }

    @Step("Проверка что раздел 'Булки' активен")
    private void assertBunsSectionActive() {
        assertTrue("Должна быть активна вкладка 'Булки'", mainPage.isBunsSectionActive());
    }

    @Step("Проверка что раздел 'Соусы' активен")
    private void assertSaucesSectionActive() {
        assertTrue("Должна быть активна вкладка 'Соусы'", mainPage.isSaucesSectionActive());
    }

    @Step("Проверка что раздел 'Начинки' активен")
    private void assertFillingsSectionActive() {
        assertTrue("Должна быть активна вкладка 'Начинки'", mainPage.isFillingsSectionActive());
    }
}