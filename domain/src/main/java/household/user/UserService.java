package household.user;

import com.google.common.base.Preconditions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public User createUser(User user) {
	    Preconditions.checkState(userRepository.determineUser(user.getEmail()) == null);
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

	public void invite(String email, Long householdId) {
		User user = userRepository.determineUser(email);
		user.addInvitation(new Invitation(null, householdId));
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
