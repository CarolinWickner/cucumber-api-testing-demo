package bdd.steps;

import bdd.support.TestContext;
import api.AuthResponse;
import api.DummyAuthApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {

    private final TestContext context = new TestContext();
    private final DummyAuthApi authApi = new DummyAuthApi();

    private Map<String, String> credentials;
    private AuthResponse response;

    /* -----------------------------
       GIVEN
       ----------------------------- */

    @Given("valid user credentials")
    public void validUserCredentials() {
        credentials = new HashMap<>();
        credentials.put("username", "user");
        credentials.put("password", "password");
    }

    @Given("invalid user credentials")
    public void invalidUserCredentials() {
        credentials = new HashMap<>();
        credentials.put("username", "invalid");
        credentials.put("password", "wrong");
    }

    /* -----------------------------
       WHEN
       ----------------------------- */

    @When("the authentication request is sent")
    public void authenticationRequestIsSent() {
        response = authApi.authenticate(credentials);
        context.put("response", response);
    }

    /* -----------------------------
       THEN
       ----------------------------- */

    @Then("the response status should be {int}")
    public void responseStatusShouldBe(int expectedStatus) {
        AuthResponse response = context.get("response");
        assertNotNull(response, "Response must not be null");
        assertEquals(expectedStatus, response.statusCode());
    }

    @Then("the response status is not {int}")
    public void responseStatusIsNot(int status) {
        AuthResponse response = context.get("response");
        assertNotNull(response);
        assertNotEquals(status, response.statusCode());
    }

    @Then("an access token is returned")
    public void accessTokenIsReturned() {
        AuthResponse response = context.get("response");
        assertNotNull(response.token(), "Token must not be null");
        assertFalse(response.token().isBlank(), "Token must not be blank");
        context.put("token", response.token());
    }

    @Then("no access token is returned")
    public void noAccessTokenIsReturned() {
        AuthResponse response = context.get("response");
        assertNull(response.token(), "Token should not be present");
    }

    @Then("the response does not contain field {string}")
    public void responseDoesNotContainField(String fieldName) {
        AuthResponse response = context.get("response");
        String responseAsString = response.toString();
        assertFalse(
                responseAsString.contains(fieldName),
                "Response should not expose field: " + fieldName
        );
    }
}