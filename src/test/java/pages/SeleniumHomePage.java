
package pages;

import org.openqa.selenium.WebDriver;

public class SeleniumHomePage {

    private final WebDriver driver;
    private static final String URL = "https://www.selenium.dev";

    public SeleniumHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
