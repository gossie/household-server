package household.user;

import java.util.function.Function;

import org.assertj.core.api.AbstractAssert;
import org.junit.Assert;

import household.cleaningplan.Chore;
import household.cleaningplan.ChoreAssert;

public class UserAssert  extends AbstractAssert<UserAssert, User> {

    private UserAssert(User actual) {
        super(actual, UserAssert.class);
    }

    public static UserAssert assertThat(User actual) {
        return new UserAssert(actual);
    }

    public UserAssert hasId(Long id) {
        Assert.assertEquals(id, actual.getId());
        return this;
    }

    public UserAssert hasEmail(String email) {
        Assert.assertEquals(email, actual.getEmail());
        return this;
    }

    public void hasPassword(Function<String, Boolean> passwordVerifier) {
        Assert.assertTrue(passwordVerifier.apply(actual.getPassword()));
    }
}
