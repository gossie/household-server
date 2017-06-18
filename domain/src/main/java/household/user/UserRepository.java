package household.user;

public interface UserRepository {
	
	User createUser(User user);
	
	User determineUser(Long userId);
	
	User determineUser(String email);

	User determineCurrentUser();

	User saveUser(User user);

}
