package household.cleaningplan;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChoreEntity extends AbstractEntity {

	private String name;
	private long lastPerformed;
	@OneToOne(cascade=CascadeType.ALL)
	private RepeatEntity repeat;
	
	public ChoreEntity(String name, long lastPerformed) {
		this(name, lastPerformed, null);
	}
	
	public Optional<RepeatEntity> getRepeat() {
		return Optional.ofNullable(repeat);
	}
}
