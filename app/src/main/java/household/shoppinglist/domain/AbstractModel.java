package household.shoppinglist.domain;

import java.util.UUID;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class AbstractModel {

	private String id;

	protected AbstractModel(String id) {
		this.id = id == null ? UUID.randomUUID().toString() : id;
	}

	public String getId() {
		return id;
	}
}
