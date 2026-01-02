package bdd.steps;

import api.AuthResponse;
import api.DummyAuthApi;
import io.cucumber.java.en.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {

    private final DummyAuthApi api = new DummyAuthApi();
    private AuthResponse response;
    private Map<String, String> payload;

    @Given("valid user credentials")
    public void validUserCredentials() {
        payload = Map.of(
                "username", "user",
                "password", "password"
        );
    }

    @When("the authentication request is sent")
    public void authenticationRequestIsSent() {
        response = api.authenticate(payload);
    }

    @Then("the response status should be {int}")
    public void responseStatusShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, response.statusCode());
    }

    @Then("an access token is returned")
    public void accessTokenIsReturned() {
        assertNotNull(response.token());
    }
}