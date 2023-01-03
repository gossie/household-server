package household.cleaningplan;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class AbstractModel {

	private final String id;

	protected AbstractModel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
