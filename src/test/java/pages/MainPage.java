package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы вкладок
    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Булки')]/parent::div")
    private WebElement bunsTab;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Соусы')]/parent::div")
    private WebElement saucesTab;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Начинки')]/parent::div")
    private WebElement fillingsTab;

    // Активные вкладки
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[contains(text(), 'Булки')]")
    private WebElement activeBunsTab;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[contains(text(), 'Соусы')]")
    private WebElement activeSaucesTab;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[contains(text(), 'Начинки')]")
    private WebElement activeFillingsTab;

    // Более безопасные методы клика через JavaScript
    @Step("Перейти к разделу 'Булки'")
    public void clickBunsSection() {
        try {
            // Пробуем обычный клик
            bunsTab.click();
        } catch (Exception e) {
            // Если обычный клик не работает, используем JavaScript
            System.out.println("Обычный клик не сработал, используем JavaScript");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", bunsTab);
        }
    }

    @Step("Перейти к разделу 'Соусы'")
    public void clickSaucesSection() {
        try {
            saucesTab.click();
        } catch (Exception e) {
            System.out.println("Обычный клик не сработал, используем JavaScript");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", saucesTab);
        }
    }

    @Step("Перейти к разделу 'Начинки'")
    public void clickFillingsSection() {
        try {
            fillingsTab.click();
        } catch (Exception e) {
            System.out.println("Обычный клик не сработал, используем JavaScript");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", fillingsTab);
        }
    }

    @Step("Проверить, что активен раздел 'Булки'")
    public boolean isBunsSectionActive() {
        try {
            return activeBunsTab.isDisplayed();
        } catch (Exception e) {
            System.out.println("Вкладка 'Булки' не активна: " + e.getMessage());
            return false;
        }
    }

    @Step("Проверить, что активен раздел 'Соусы'")
    public boolean isSaucesSectionActive() {
        try {
            return activeSaucesTab.isDisplayed();
        } catch (Exception e) {
            System.out.println("Вкладка 'Соусы' не активна: " + e.getMessage());
            return false;
        }
    }

    @Step("Проверить, что активен раздел 'Начинки'")
    public boolean isFillingsSectionActive() {
        try {
            return activeFillingsTab.isDisplayed();
        } catch (Exception e) {
            System.out.println("Вкладка 'Начинки' не активна: " + e.getMessage());
            return false;
        }
    }

    // Остальные методы остаются без изменений
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти в аккаунт')]")
    private WebElement loginAccountButton;

    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/account')]")
    private WebElement personalAccountLink;

    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/') and contains(@class, 'AppHeader_header__link')]")
    private WebElement constructorLink;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private WebElement logo;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Оформить заказ')]")
    private WebElement placeOrderButton;

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        try {
            loginAccountButton.click();
        } catch (Exception e) {
            System.out.println("Ошибка при клике на кнопку входа: " + e.getMessage());
            throw e;
        }
    }

    @Step("Нажать на 'Личный Кабинет'")
    public void clickPersonalAccountLink() {
        personalAccountLink.click();
    }

    @Step("Нажать на 'Конструктор'")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        logo.click();
    }

    @Step("Проверить, что пользователь авторизован")
    public boolean isUserLoggedIn() {
        try {
            // Проверяем несколько признаков авторизации
            boolean isPlaceOrderButtonVisible = false;

            try {
                isPlaceOrderButtonVisible = placeOrderButton.isDisplayed();
            } catch (Exception e) {
                // Кнопка оформления заказа не найдена - возможно пользователь не авторизован
            }

            System.out.println("Признаки авторизации - кнопка заказа: " + isPlaceOrderButtonVisible);

            return isPlaceOrderButtonVisible;
        } catch (Exception e) {
            System.out.println("Ошибка проверки авторизации: " + e.getMessage());
            return false;
        }
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForMainPageLoaded() {
        // Просто ждем немного вместо сложных ожиданий
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}