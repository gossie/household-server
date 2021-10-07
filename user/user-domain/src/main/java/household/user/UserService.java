package household.user;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final List<UserObserver> userObservers;

    public User createUser(User user) {
        userRepository.determineUser(user.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException();
        });
        return userRepository.createUser(user);
    }

    public Publisher<User> determineCurrentUser() {
        return userRepository.determineCurrentUser();
    }

    public User determineUser(Long userId) {
        return userRepository.determineUser(userId);
    }

    public List<User> determineUsers(Long householdId) {
        return userRepository.determineUsers(householdId);
    }

    public void updateUser(User user) {
        userRepository.saveUser(user);
    }

    public void invite(String email, User sender) {
        User user = userRepository.determineUser(email).orElseThrow(() -> new IllegalStateException("no user with email [" + email + "]"));
        user.addInvitation(new Invitation(null, sender.getHouseholdId(), sender.getEmail()));
        userRepository.saveUser(user);
    }

    public void rejectInvitation(Long userId, Long invitationId) {
        User user = userRepository.determineUser(userId);
        user.rejectInvitation(invitationId);
        userRepository.saveUser(user);
    }

    public void acceptInvitation(Long userId, Long invitationId) {
        User user = userRepository.determineUser(userId);
        Long oldHouseholdId = user.getHouseholdId();
        user.acceptInvitation(invitationId);
        userRepository.saveUser(user);

        List<Long> leftUserIds = userRepository.determineUsers(oldHouseholdId).stream()
        		.map(User::getId)
        		.collect(Collectors.toList());
        
        userObservers.forEach(observer -> observer.onInvitationAccepted(new InvitationAcceptedEvent(oldHouseholdId, leftUserIds)));
    }

    public void removeHouseholdFromUsers(Long householdId) {
        userRepository.determineUsers(householdId).forEach(user -> {
            user.setHouseholdId(null);
            userRepository.saveUser(user);
        });
    }

    public User changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.determineUser(userId);
        user.setPassword(newPassword);
        return userRepository.saveUserAndHashPassword(user, currentPassword);
    }
}
