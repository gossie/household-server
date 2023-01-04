package household.user.domain;

import java.util.function.Predicate;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class UserAssert extends AbstractAssert<UserAssert, User> {

    private UserAssert(User actual) {
		super(actual, UserAssert.class);
	}

	public static UserAssert assertThat(User actual) {
		return new UserAssert(actual);
	}

    public UserAssert hasId(String id) {
        Assertions.assertThat(actual.getId()).isEqualTo(id);
        return this;
    }

    public UserAssert hasEmail(String email) {
        Assertions.assertThat(actual.getEmail()).isEqualTo(email);
        return this;
    }

    public UserAssert hasPassword(Predicate<String> passwordChecker) {
        Assertions.assertThat(passwordChecker.test(actual.getPassword())).isTrue();
        return this;
    }
    
}
