package household.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;

import org.assertj.core.api.AbstractAssert;

public class UserAssert  extends AbstractAssert<UserAssert, User> {

    private UserAssert(User actual) {
        super(actual, UserAssert.class);
    }

    public static UserAssert assertThat(User actual) {
        return new UserAssert(actual);
    }

    public UserAssert hasId(Long id) {
        assertEquals(id, actual.getId());
        return this;
    }

    public UserAssert hasEmail(String email) {
        assertEquals(email, actual.getEmail());
        return this;
    }

    public void hasPassword(Function<String, Boolean> passwordVerifier) {
        assertTrue(passwordVerifier.apply(actual.getPassword()));
    }
}
