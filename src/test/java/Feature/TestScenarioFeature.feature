@regression
Feature: TestScenarioFeature

  @smoke
  Scenario: Verify that admin user is able to login with valid credentials
    Given I login with "Admin User"
    When I click on "Scenarios"
    And I click on create new scenario button
