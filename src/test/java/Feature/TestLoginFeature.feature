@regression
Feature: TestLoginFeature

  @smoke
  Scenario Outline: Verify that user is able to login with valid credentials
    Given I login with "<User Type>"
    Then I verify user navigate to "<User>" Dashboard
    Examples:
      | User Type  | User  |
      | Admin User | Admin |
      | Guest User | Guest |
