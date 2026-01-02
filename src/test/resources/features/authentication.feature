Feature: Authentication API

  Scenario: Successful authentication with valid credentials
    Given valid user credentials
    When the authentication request is sent
    Then the response status should be 200
    And an access token is returned

  Scenario: Authentication fails with invalid credentials
    Given invalid user credentials
    When the authentication request is sent
    Then the response status should be 401
    And no access token is returned

  Scenario: Authentication response does not expose sensitive data
    Given invalid user credentials
    When the authentication request is sent
    Then the response does not contain field "password"
