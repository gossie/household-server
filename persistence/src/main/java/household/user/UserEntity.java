package household.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private final String email;
	private String password;
	private Long householdId;
	@OneToMany(cascade=CascadeType.ALL)
	private final List<InvitationEntity> invitations = new ArrayList<>();
	
	public void addInvitation(InvitationEntity invitation) {
		invitations.add(invitation);
	}
}
