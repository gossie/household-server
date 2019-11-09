package household.user;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    User determineUser(Long userId);

    Optional<User> determineUser(String email);

    Mono<User> determineCurrentUser();

    List<User> determineUsers(Long householdId);

    User saveUser(User user);

    User saveUserAndHashPassword(User user, String currentPassword);

}
