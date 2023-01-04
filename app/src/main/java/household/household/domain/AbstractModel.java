package household.household.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class AbstractModel {

	private String id;

	protected AbstractModel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
