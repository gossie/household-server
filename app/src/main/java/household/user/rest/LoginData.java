package household.user.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginData(@JsonProperty("email") String email, @JsonProperty("password") String password) {
}
