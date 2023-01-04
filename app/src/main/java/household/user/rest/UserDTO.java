package household.user.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
class UserDTO extends AbstractDTO {

	private final String databaseId;
	private final String email;
	private final String householdId;
	private final List<InvitationDTO> invitations;

	@JsonIgnore
	public String getHouseholdId() {
		return householdId;
	}

}
