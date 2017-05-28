package household.shoppinglist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import household.AbstractModel;

public class ShoppingList extends AbstractModel {

	private final List<ShoppingListItem> shoppingListItems;
	
    ShoppingList(Long id, List<ShoppingListItem> shoppingListItems) {
    	super(id);
		this.shoppingListItems = new ArrayList<>(shoppingListItems);
	}

	public ShoppingList(Long id) {
		this(id, new ArrayList<>());
	}

	public void clearSelectedItems() {
		shoppingListItems.removeIf(ShoppingListItem::isSelected);
	}
	
	public void update(ShoppingListItem shoppingListItem) {
		shoppingListItems.stream()
				.filter(item -> item.getName().equals(shoppingListItem.getName()))
				.findFirst()
				.ifPresent(item -> item.setSelected(shoppingListItem.isSelected()));
	}

	public void addShoppingListItem(ShoppingListItem shoppingListItem) {
		shoppingListItems.add(shoppingListItem);
	}

	public List<ShoppingListItem> getShoppingListItems() {
		return Collections.unmodifiableList(shoppingListItems);
	}
}
