package household.user.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import household.user.events.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final List<UserObserver> userObservers;

    public User createUser(User user) {
        userRepository.determineUserByEmail(user.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException();
        });
        return userRepository.createUser(user);
    }

    public Optional<User> determineUserByEmail(String email) {
        return userRepository.determineUserByEmail(email);
    }

    public User determineUserById(String userId) {
        return userRepository.determineUserById(userId);
    }

    public List<User> determineUsers(String householdId) {
        return userRepository.determineUsers(householdId);
    }

    public void updateUser(User user) {
        userRepository.saveUser(user);
    }

    public void invite(String email, User sender) {
        User user = userRepository.determineUserByEmail(email).orElseThrow(() -> new IllegalStateException("no user with email [" + email + "]"));
        user.addInvitation(new Invitation(null, sender.getHouseholdId(), sender.getEmail()));
        userRepository.saveUser(user);
    }

    public void rejectInvitation(String userId, String invitationId) {
        User user = userRepository.determineUserById(userId);
        user.rejectInvitation(invitationId);
        userRepository.saveUser(user);
    }

    public void acceptInvitation(String userId, String invitationId) {
        User user = userRepository.determineUserById(userId);
        String oldHouseholdId = user.getHouseholdId();
        user.acceptInvitation(invitationId);
        userRepository.saveUser(user);

        List<String> leftUserIds = userRepository.determineUsers(oldHouseholdId).stream()
        		.map(User::getId)
        		.collect(Collectors.toList());

        userObservers.forEach(observer -> observer.onInvitationAccepted(new InvitationAcceptedEvent(oldHouseholdId, leftUserIds)));
    }

    public void removeHouseholdFromUsers(String householdId) {
        userRepository.determineUsers(householdId).forEach(user -> {
            user.setHouseholdId(null);
            userRepository.saveUser(user);
        });
    }

    public User changePassword(String userId, String currentPassword, String newPassword) {
        User user = userRepository.determineUserById(userId);
        user.setPassword(newPassword);
        return userRepository.saveUserAndHashPassword(user, currentPassword);
    }
}
