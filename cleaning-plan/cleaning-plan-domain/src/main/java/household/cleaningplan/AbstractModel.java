package household.cleaningplan;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class AbstractModel {

	private final Long id;

	protected AbstractModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
