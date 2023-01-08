package household.user.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRegistrationData(@JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("passwordAgain") String passwordAgain) {
}
