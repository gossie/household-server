package household.user;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final EventBus eventBus;
    private final UserRepository userRepository;

    public void init() {
        eventBus.register(this);
    }

    public User createUser(User user) {
        userRepository.determineUser(user.getEmail().toLowerCase()).ifPresent(u -> {
            throw new UserAlreadyExistsException();
        });
        return userRepository.createUser(user);
    }

    public User determineCurrentUser() {
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
        eventBus.post(new InvitationAcceptedEvent(oldHouseholdId, leftUsers));
    }

    @Subscribe
    public void onHouseholdDeletion(HouseholdDeletedEvent event) {
        userRepository.determineUsers(event.getHousehold().getId()).forEach(user -> {
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
