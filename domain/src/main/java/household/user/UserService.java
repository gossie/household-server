package household.user;

public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(User user) {
		return userRepository.createUser(user);
	}
}
