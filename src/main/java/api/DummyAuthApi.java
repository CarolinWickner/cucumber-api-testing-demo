package api;

import java.util.Map;

public class DummyAuthApi {

    public AuthResponse authenticate(Map<String, String> payload) {
        if ("user".equals(payload.get("username"))
                && "password".equals(payload.get("password"))) {
            return new AuthResponse(200, "dummy-token");
        }
        return new AuthResponse(401, null);
    }
}