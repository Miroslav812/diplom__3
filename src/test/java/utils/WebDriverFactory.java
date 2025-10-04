package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");

        // Всегда используем ChromeDriver для Яндекс.Браузера (они совместимы)
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        if (browser.equalsIgnoreCase("yandex")) {
            // Принудительно устанавливаем версию драйвера, совместимую с Яндекс.Браузером 138
            setupYandexCompatibleDriver();

            String yandexPath = findYandexBrowserPath();
            if (yandexPath != null) {
                options.setBinary(yandexPath);
                System.out.println("Using Yandex Browser at: " + yandexPath);
            } else {
                System.out.println("Yandex Browser not found, using Chrome instead");
            }
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static void setupYandexCompatibleDriver() {
        try {
            // Принудительно устанавливаем ChromeDriver версии 138.x
            WebDriverManager.chromedriver().driverVersion("138.0.7045.42").setup();
        } catch (Exception e) {
            System.out.println("Error setting Yandex-compatible driver: " + e.getMessage());
            // Если не удалось, используем стандартную настройку
            WebDriverManager.chromedriver().setup();
        }
    }

    private static String findYandexBrowserPath() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            String[] possiblePaths = {
                    "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe",
                    "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe",
                    "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe"
            };

            for (String path : possiblePaths) {
                if (new java.io.File(path).exists()) {
                    return path;
                }
            }
        }
        return null;
    }

    public static String getBaseUrl() {
        return "https://stellarburgers.nomoreparties.site";
    }
}