@accessibility @regression
Feature: NEXUS Application Accessibility Compliance

  Scenario: Audit NEXUS Dashboard for WCAG 2.1 AA accessibility standards
    Given I open the NEXUS application
    Then the dashboard should be displayed
    And the page should be audited for accessibility violations