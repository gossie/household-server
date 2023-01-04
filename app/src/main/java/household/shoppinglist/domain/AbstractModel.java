package household.shoppinglist.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class AbstractModel {

	private String id;

	protected AbstractModel(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
