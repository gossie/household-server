package household.user;

import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    User determineUser(Long userId);

    Optional<User> determineUser(String email);

    User determineCurrentUser();

    User saveUser(User user);

}
