package household.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
class InvitationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private final Long householdId;
	private final String sender;
	
}
