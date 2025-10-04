package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAccountPage extends BasePage {

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Профиль')]")
    private WebElement profileTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'История заказов')]")
    private WebElement orderHistoryTab;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Выход')]")
    private WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]")
    private WebElement profileDescription;

    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        waitForPageLoaded();
    }

    @Step("Проверить, что отображается страница профиля")
    public boolean isProfilePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileDescription));
            return profileDescription.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Дождаться загрузки контента личного кабинета")
    public void waitForAccountContentVisible() {
        wait.until(ExpectedConditions.visibilityOf(profileDescription));
    }
}