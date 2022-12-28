package household.user;

import com.fasterxml.jackson.annotation.JsonProperty;

record UserRegistrationData(@JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("passwordAgain") String passwordAgain) {
}
