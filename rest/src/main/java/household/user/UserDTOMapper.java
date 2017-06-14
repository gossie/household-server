package household.user;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class UserDTOMapper {

	UserDTO map(User user) {
		return new UserDTO(user.getId(), user.getEmail(), user.getHouseholdId());
	}

}
