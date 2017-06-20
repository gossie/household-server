package household.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public User createUser(User user) {
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
		user.removeInvitation(invitationId);
		userRepository.saveUser(user);
	}
}
