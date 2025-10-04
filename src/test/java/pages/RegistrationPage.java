package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Более надежные локаторы
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameInputField;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInputField;

    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private WebElement passwordInputField;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'input__error')]")
    private WebElement passwordErrorMessage;

    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private WebElement registrationTitle;

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInputField));
        nameInputField.clear();
        nameInputField.sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInputField));
        emailInputField.clear();
        emailInputField.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    @Step("Выполнить регистрацию пользователя")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
            return passwordErrorMessage.getText();
        } catch (Exception e) {
            return "No error message found";
        }
    }

    @Step("Проверить, что отображается форма регистрации")
    public boolean isRegistrationFormDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registrationTitle));
            return registrationTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Дождаться загрузки страницы регистрации")
    public void waitForRegistrationPageLoaded() {
        wait.until(ExpectedConditions.urlContains("/register"));
        wait.until(ExpectedConditions.visibilityOf(registrationTitle));
    }

    @Step("Проверить наличие ошибки")
    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
            return passwordErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}