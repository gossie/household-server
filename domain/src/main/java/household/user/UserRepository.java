package household.user;

public interface UserRepository {
	
	User createUser(User user);
	
	User determineUser(String email);

	User determineCurrentUser();

	User saveUser(User user);

}
