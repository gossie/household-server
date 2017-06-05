package household.cleaningplan;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
class ChoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	private String name;
	private long lastPerformed;
	@OneToOne(cascade=CascadeType.ALL)
	private RepeatEntity repeat;
	
	Optional<RepeatEntity> getRepeat() {
		return Optional.ofNullable(repeat);
	}
}
