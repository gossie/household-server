package household.cleaningplan;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
class ChoreEntity {

	@Id
	private final Long id;
	private String name;
	private long lastPerformed;
	@OneToOne(cascade= CascadeType.ALL, orphanRemoval=true)
	private RepeatEntity repeat;

	Optional<RepeatEntity> getRepeat() {
		return Optional.ofNullable(repeat);
	}
}
