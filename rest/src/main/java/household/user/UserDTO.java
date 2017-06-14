package household.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import household.AbstractDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class UserDTO extends AbstractDTO {

	private final Long databaseId;
	private final String email;
	private final Long householdId;
	
	@JsonIgnore
	public Long getHouseholdId() {
		return householdId;
	}

}
