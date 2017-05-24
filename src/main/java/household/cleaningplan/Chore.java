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
public class Chore extends AbstractEntity {

	private String name;
	private long lastPerformed;
	@OneToOne(cascade=CascadeType.ALL)
	private Repeat repeat;
	
	public Chore(String name, long lastPerformed) {
		this(name, lastPerformed, null);
	}
	
	public Optional<Repeat> getRepeat() {
		return Optional.ofNullable(repeat);
	}
}
