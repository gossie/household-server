package household.user;

class UserMapper {

	User map(UserEntity user) {
		return new User(user.getId(), user.getEmail(), user.getPassword());
	}
	
	UserEntity map(User user) {
		return new UserEntity(user.getId(), user.getEmail(), user.getPassword());
	}
}
