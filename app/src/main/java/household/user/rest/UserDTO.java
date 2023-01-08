package household.user.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PACKAGE)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class UserDTO extends AbstractDTO {

	private String databaseId;
	private String email;
	private String householdId;
	private List<InvitationDTO> invitations;

	@JsonIgnore
	public String getHouseholdId() {
		return householdId;
	}

}
