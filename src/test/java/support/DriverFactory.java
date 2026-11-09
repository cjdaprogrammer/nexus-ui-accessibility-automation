package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void createDriver() {

        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        driver.set(webDriver);
    }

    public static WebDriver getDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver == null) {
            throw new IllegalStateException(
                "WebDriver has not been initialized."
            );
        }

        return webDriver;
    }

    public static void quitDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            try {
                webDriver.quit();
            } finally {
                driver.remove();
            }
        }
    }
}