package household.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.createUser(user);
	}
	
	public User getCurrentUser() {
	    return userRepository.determineCurrentUser();
	}

	public User determineUser(String email) {
		return userRepository.determineUser(email);
	}

	public void updateUser(User user) {
		userRepository.saveUser(user);
	}

	public void invite(String email, Long householdId) {
		User user = userRepository.determineUser(email);
		user.addInvitation(new Invitation(householdId));
		userRepository.saveUser(user);
	}
}
