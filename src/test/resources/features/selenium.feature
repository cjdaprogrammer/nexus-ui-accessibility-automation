Feature: Selenium website verification

  Scenario: Verify Selenium website title
    Given I open the Selenium website
    Then the page title should contain "Selenium"

  Scenario: Verify Google website title
    Given I open the Google website
    Then the page title should contain "Google"
