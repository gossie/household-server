package household.user;

class UserMapper {

	User map(UserEntity userEntity) {
		User user = new User(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword());
		user.setHouseholdId(userEntity.getHouseholdId());
		return user;
	}
	
	UserEntity map(User user) {
		UserEntity userEntity = new UserEntity(user.getId(), user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setHouseholdId(user.getHouseholdId());
		return userEntity;
	}
}
