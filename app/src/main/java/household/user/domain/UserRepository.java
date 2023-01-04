package household.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    User determineUserById(String userId);

    Optional<User> determineUserByEmail(String email);

    List<User> determineUsers(String householdId);

    User saveUser(User user);

    User saveUserAndHashPassword(User user, String currentPassword);

}
