package household.user.persistence;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class UserEntity {

	@Id
	private String id;
	private String email;
	private String password;
	private String householdId;

	private List<InvitationEntity> invitations = new ArrayList<>();

	UserEntity(String id, String email) {
		this.id = id;
		this.email = email;
	}

	public void addInvitation(InvitationEntity invitation) {
		invitations.add(invitation);
	}
}
