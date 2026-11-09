
Feature: NEXUS Dashboard

  As a user
  I want to access the NEXUS dashboard
  So that I can view my productivity information

  Background:
    Given I open the NEXUS application

  @smoke
  Scenario: Verify NEXUS dashboard loads
    Then the dashboard should be displayed
    And the NEXUS heading should be visible

  @navigation
  Scenario: Verify dashboard navigation
    Then I should see the following navigation items:
      | Dashboard         |
      | Goals             |
      | Projects          |
      | Habits            |
      | Skills            |
      | Knowledge         |
      | AI Assistant      |
      | Future Simulator  |
      | Settings          |

  @dashboard
  Scenario: Verify productivity information
    Then the productivity section should be visible
    And the current state section should be visible
    And the today's focus section should be visible

  @dashboard
  Scenario: Verify dashboard action buttons
    Then the Add Task button should be visible
    And the View All button should be visible
    And the Ask NEXUS button should be visible
