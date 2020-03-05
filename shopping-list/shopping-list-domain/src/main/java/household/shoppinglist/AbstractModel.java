package household.shoppinglist;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class AbstractModel {

	private Long id;

	protected AbstractModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
