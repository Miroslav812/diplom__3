package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Ожидание  загрузки страницы")
    public void waitForPageLoaded() {
        wait.until(webDriver ->
                webDriver != null &&
                        ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }
}