package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NexusDashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://nexus-personalos.vercel.app/";

    public NexusDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get(URL);
        wait.until(webDriver ->
            ((org.openqa.selenium.JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isDashboardLoaded() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))
        ).isDisplayed();
    }
    
    /**
     * Checks if text is visible on the page, with tolerance for:
     * - Client-side hydration delays (waits up to 5s)
     * - Straight apostrophe (') vs typographic curly apostrophe (’)
     * - Split inner child elements
     */
    public boolean isTextVisible(String text) {
        String straightApos = text.replace("’", "'");
        String curlyApos = text.replace("'", "’");

        // Match either straight apostrophe or curly apostrophe
        String xpath = "//*[contains(normalize-space(.), " + escapeXpathString(straightApos) + ") or " +
                       "contains(normalize-space(.), " + escapeXpathString(curlyApos) + ") or " +
                       "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), " +
                       "translate(" + escapeXpathString(straightApos) + ", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";

        try {
            // Wait briefly for React client-side components to mount
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> {
                        List<WebElement> elements = d.findElements(By.xpath(xpath));
                        return elements.stream().anyMatch(WebElement::isDisplayed);
                    });
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if a button or link with the given text exists (case-tolerant).
     */
    public boolean isButtonVisible(String buttonName) {
        String safeName = escapeXpathString(buttonName);
        String xpath = "//button[contains(normalize-space(.), " + safeName + ")] | " +
                       "//a[contains(normalize-space(.), " + safeName + ")] | " +
                       "//*[@role='button'][contains(normalize-space(.), " + safeName + ")]";

        List<WebElement> buttons = driver.findElements(By.xpath(xpath));
        return buttons.stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean isNavigationItemVisible(String itemName) {
        String safeName = escapeXpathString(itemName);
        String xpath = "//*[self::a or self::button or @role='link' or @role='button']" +
                       "[contains(normalize-space(.), " + safeName + ")]";

        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return elements.stream().anyMatch(WebElement::isDisplayed);
    }

    /**
     * Matches action elements (buttons, links, clickable tags) even if followed
     * by icons, arrows (e.g., 'View All →'), or extra whitespace.
     */
    public boolean isActionVisible(String actionName) {
        String safeName = escapeXpathString(actionName);
        String xpath = "//*[self::button or self::a or @role='button' or contains(@class, 'btn') or contains(@class, 'action')]" +
                       "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), " +
                       "translate(" + safeName + ", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]";

        List<WebElement> actions = driver.findElements(By.xpath(xpath));
        return actions.stream().anyMatch(WebElement::isDisplayed);
    }

    /**
     * Helper to safely format XPath string arguments containing apostrophes/single quotes.
     */
    private String escapeXpathString(String string) {
        if (!string.contains("'")) {
            return "'" + string + "'";
        } else if (!string.contains("\"")) {
            return "\"" + string + "\"";
        } else {
            // If it contains both ' and ", use concat()
            return "concat('" + string.replace("'", "', \"'\", '") + "')";
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}