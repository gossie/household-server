package household.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        userRepository.determineUser(user.getEmail()).ifPresent(u -> {
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
        user.acceptInvitation(invitationId);
        userRepository.saveUser(user);
    }
}
