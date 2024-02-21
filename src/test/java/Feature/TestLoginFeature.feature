Feature: TestLoginFeature

  Scenario: Verify that user is able to login with superuser valid credentials
    Given I login with "Super User"
    Then I verify user navigate to homescreen


  Scenario: Verify that user is able to login with company valid credentials
    Given I login with "Company User"
    Then I verify user navigate to homescreen
