
package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.testng.Assert;
import java.util.List;
import org.testng.Assert;

import pages.NexusDashboardPage;
import support.DriverFactory;


public class NexusDashboardSteps {

    private NexusDashboardPage nexusPage;

    @Given("I open the NEXUS application")
    public void openNexusApplication() {

        nexusPage = new NexusDashboardPage(
            DriverFactory.getDriver()
        );

        nexusPage.open();

        System.out.println(
            "NEXUS Page Title: " + nexusPage.getPageTitle()
        );
    }

    @Then("the dashboard should be displayed")
    public void verifyDashboardDisplayed() {

        Assert.assertTrue(
            nexusPage.isDashboardLoaded(),
            "NEXUS dashboard body was not displayed."
        );
    }

    @Then("the NEXUS heading should be visible")
    public void verifyNexusHeading() {

        Assert.assertTrue(
            nexusPage.isTextVisible("NEXUS"),
            "NEXUS heading was not visible."
        );
    }

    @Then("I should see the following navigation items:")
    public void verifyNavigationItems(DataTable dataTable) {

        List<String> navigationItems =
            dataTable.asList(String.class);

        for (String item : navigationItems) {

            Assert.assertTrue(
                nexusPage.isNavigationItemVisible(item),
                "Navigation item was not visible: " + item
            );

            System.out.println(
                "Verified navigation item: " + item
            );
        }
    }

    @Then("the productivity section should be visible")
    public void verifyProductivitySection() {

        Assert.assertTrue(
            nexusPage.isTextVisible("Productivity"),
            "Productivity section was not visible."
        );
    }

    @Then("the current state section should be visible")
    public void verifyCurrentStateSection() {

        Assert.assertTrue(
            nexusPage.isTextVisible("Current State"),
            "Current State section was not visible."
        );
    }

    @Then("the today's focus section should be visible")
        public void verifyTodaysFocusSection() {
            boolean isVisible = nexusPage.isTextVisible("Today's Focus") 
                     || nexusPage.isTextVisible("Today’s Focus") 
                     || nexusPage.isTextVisible("Focus");
            Assert.assertTrue(isVisible, "Today's Focus section was not visible.");
    }

    @Then("the Add Task button should be visible")
    public void verifyAddTaskButton() {

        Assert.assertTrue(
            nexusPage.isButtonVisible("Add Task"),
            "Add Task button was not visible."
        );
    }

    @Then("the View All button should be visible")
    public void verifyViewAllButton() {

        Assert.assertTrue(
            nexusPage.isActionVisible("View All"),
            "View All control was not visible."
        );
    }

    @Then("the Ask NEXUS button should be visible")
    public void verifyAskNexusButton() {

        Assert.assertTrue(
            nexusPage.isButtonVisible("Ask NEXUS"),
            "Ask NEXUS button was not visible."
        );
    }


    @And("the page should be audited for accessibility violations")
    public void auditAccessibilityViolations() {
        // Runs an automated WCAG 2.1 Level A & AA scan
        Results results = new AxeBuilder()
                .withTags(List.of("wcag2a", "wcag2aa", "wcag21a", "wcag21aa"))
                .analyze(nexusPage.getDriver());

        List<Rule> violations = results.getViolations();

        System.out.println("==================================================");
        System.out.println(" ACCESSIBILITY (a11y) AUDIT REPORT");
        System.out.println(" Total Violations Found: " + violations.size());
        System.out.println("==================================================");

        for (Rule violation : violations) {
            System.out.println("❌ [" + violation.getId() + "] " + violation.getDescription());
            System.out.println("   Impact: " + violation.getImpact());
            System.out.println("   Help URL: " + violation.getHelpUrl());
            System.out.println("   Elements affected: " + violation.getNodes().size());
            System.out.println("--------------------------------------------------");
        }

        // For interview demonstration: log all violations or assert critical issues
        long criticalViolations = violations.stream()
                .filter(v -> "critical".equalsIgnoreCase(v.getImpact()))
                .count();

        // Optional: fail if there are critical violations
        // Assert.assertEquals(criticalViolations, 0, "Critical accessibility violations found!");
    }

    /**
     * 
     */
    @Then("the dashboard page should meet WCAG accessibility standards")
    public void theDashboardPageShouldMeetWCAGAccessibilityStandards() {
    Results results = new AxeBuilder()
            .withTags(List.of("wcag2a", "wcag2aa", "wcag21aa"))
            .analyze(nexusPage.getDriver());

    List<Rule> violations = results.getViolations();
    if (!violations.isEmpty()) {
        StringBuilder report = new StringBuilder("Found " + violations.size() + " accessibility issues:\n");
        for (Rule violation : violations) {
            report.append("- [").append(violation.getId()).append("]: ")
                  .append(violation.getDescription())
                  .append(" (Impact: ").append(violation.getImpact()).append(")\n");
        }
        Assert.fail(report.toString());
    }
}
}