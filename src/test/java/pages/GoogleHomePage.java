
package pages;

import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private final WebDriver driver;
    private static final String URL = "https://www.google.com";

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
