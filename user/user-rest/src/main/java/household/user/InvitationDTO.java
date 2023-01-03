package household.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
class InvitationDTO extends AbstractDTO {

	private final String databaseId;
	private final String householdId;
    private final String sender;

	@JsonIgnore
	public String getHouseholdId() {
		return householdId;
	}

}
