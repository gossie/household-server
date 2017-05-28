package household.shoppinglist;

import household.AbstractModel;

public class ShoppingListItem extends AbstractModel {

	private String name;
	private boolean selected;
	
	ShoppingListItem(Long id, String name, boolean selected) {
		super(id);
		this.name = name;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
