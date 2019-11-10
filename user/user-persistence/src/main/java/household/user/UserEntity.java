package household.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
	private final List<InvitationEntity> invitations = new ArrayList<>();

	public void addInvitation(InvitationEntity invitation) {
		invitations.add(invitation);
	}
}
