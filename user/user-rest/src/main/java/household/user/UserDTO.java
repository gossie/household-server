package household.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
class UserDTO extends AbstractDTO {

	private final Long databaseId;
	private final String email;
	private final Long householdId;
	private final List<InvitationDTO> invitations;

	@JsonIgnore
	public Long getHouseholdId() {
		return householdId;
	}

}
