Feature: Authentication API

  Scenario: Successful authentication with valid credentials
    Given valid user credentials
    When the authentication request is sent
    Then the response status should be 200
    And an access token is returned