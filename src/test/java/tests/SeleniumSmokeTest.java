
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumSmokeTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.out.println("Starting Chrome browser...");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifySeleniumWebsiteTitle() {

        driver.get("https://www.selenium.dev");

        String actualTitle = driver.getTitle();

        System.out.println("Website Title: " + actualTitle);

        Assert.assertTrue(
            actualTitle.contains("Selenium"),
            "Expected Selenium to appear in the website title."
        );
    }

    @Test
    public void verifyGoogleWebsiteTitle() {

        driver.get("https://www.google.com");

        String actualTitle = driver.getTitle();

        System.out.println("Website Title: " + actualTitle);

        Assert.assertTrue(
            actualTitle.contains("Google"),
            "Expected Google to appear in the website title."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            System.out.println("Browser closed successfully.");
        }
    }
}
