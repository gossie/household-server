package household.user;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final List<UserServiceObserver> userServiceObservers = new ArrayList<>();

    void addObserver(UserServiceObserver observer) {
        userServiceObservers.add(observer);
    }

    public User createUser(User user) {
        userRepository.determineUser(user.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException();
        });

        User createdUser = userRepository.createUser(user);
        userServiceObservers.forEach(UserServiceObserver::onUserCreation);

        return createdUser;
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

        List<User> leftUsers = userRepository.determineUsers(oldHouseholdId);
        // TODO: eventBus.post(new InvitationAcceptedEvent(oldHouseholdId, leftUsers));
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
