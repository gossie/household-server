package household.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UserMapper {

	private final InvitationEntityMapper invitationMapper;

	User map(UserEntity userEntity) {
		User user = new User(userEntity.getId(), userEntity.getEmail().toLowerCase(), userEntity.getPassword());
		user.setHouseholdId(userEntity.getHouseholdId());
		userEntity.getInvitations().stream().forEach(invitation -> user.addInvitation(invitationMapper.map(invitation)));
		return user;
	}

	UserEntity map(User user) {
		UserEntity userEntity = new UserEntity(user.getId(), user.getEmail().toLowerCase());
		userEntity.setPassword(user.getPassword());
		userEntity.setHouseholdId(user.getHouseholdId());
		user.getInvitations().forEach(invitation -> userEntity.addInvitation(invitationMapper.map(invitation)));
		return userEntity;
	}
}
