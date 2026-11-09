package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.testng.Assert;

import pages.GoogleHomePage;
import pages.SeleniumHomePage;
import support.DriverFactory;

public class SeleniumSteps {

    private SeleniumHomePage seleniumHomePage;
    private GoogleHomePage googleHomePage;

    @Given("I open the Selenium website")
    public void openSeleniumWebsite() {

        seleniumHomePage =
            new SeleniumHomePage(DriverFactory.getDriver());

        seleniumHomePage.open();
    }

    @Given("I open the Google website")
    public void openGoogleWebsite() {

        googleHomePage =
            new GoogleHomePage(DriverFactory.getDriver());

        googleHomePage.open();
    }

    @Then("the page title should contain {string}")
    public void verifyPageTitle(String expectedTitle) {

        String actualTitle;

        if (expectedTitle.equalsIgnoreCase("Selenium")) {
            actualTitle = seleniumHomePage.getPageTitle();

        } else if (expectedTitle.equalsIgnoreCase("Google")) {
            actualTitle = googleHomePage.getPageTitle();

        } else {
            Assert.fail(
                "Unsupported expected title: " + expectedTitle
            );
            return;
        }

        Assert.assertTrue(
            actualTitle.contains(expectedTitle),
            "Expected title to contain: " + expectedTitle
                + ", but actual title was: " + actualTitle
        );
    }
}