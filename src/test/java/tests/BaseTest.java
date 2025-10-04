package tests;

import clients.UserClient;
import io.qameta.allure.Step;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.TestDataGenerator;
import utils.WebDriverFactory;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected PersonalAccountPage personalAccountPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected UserClient userClient;
    protected User testUser;

    @Before
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(WebDriverFactory.getBaseUrl());

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userClient = new UserClient();

        // Создание тестового пользователя
        testUser = new User(
                TestDataGenerator.generateEmail(),
                "Password123",
                TestDataGenerator.generateName()
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Переход на главную страницу")
    protected void goToMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        // Просто ждем немного вместо сложных ожиданий
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Авторизация пользователя: {email}")
    protected void simpleLogin(String email, String password) {
        // Простой логин без сложных ожиданий
        goToMainPage();

        // Кликаем на кнопку входа
        try {
            mainPage.clickLoginAccountButton();
        } catch (Exception e) {
            // Если кнопка не найдена, возможно пользователь уже авторизован
            System.out.println("Кнопка входа не найдена, возможно уже авторизованы");
            return;
        }

        // Ждем немного
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Заполняем форму
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Ждем завершения
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Выход из системы")
    protected void simpleLogout() {
        // Простой выход
        goToMainPage();

        // Кликаем на личный кабинет
        try {
            mainPage.clickPersonalAccountLink();
        } catch (Exception e) {
            System.out.println("Не удалось перейти в личный кабинет: " + e.getMessage());
            return;
        }

        // Ждем
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Выходим
        try {
            personalAccountPage.clickLogoutButton();
        } catch (Exception e) {
            System.out.println("Не удалось выйти: " + e.getMessage());
        }

        // Ждем
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверка что находимся на странице логина")
    protected boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/login");
    }

    @Step("Проверка что находимся на главной странице")
    protected boolean isOnMainPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://stellarburgers.nomoreparties.site/") ||
                currentUrl.equals("https://stellarburgers.nomoreparties.site");
    }
}