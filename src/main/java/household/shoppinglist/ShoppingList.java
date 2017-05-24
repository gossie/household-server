package household.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class ShoppingList extends AbstractEntity {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ShoppingListItem> shoppingListItems = new ArrayList<>();

	public void clearSelectedItems() {
		shoppingListItems.removeIf(ShoppingListItem::isSelected);
	}

	public void addShoppingListItem(ShoppingListItem shoppingListItem) {
		shoppingListItems.add(shoppingListItem);
	}
	
	public void update(ShoppingListItem shoppingListItem) {
		shoppingListItems.stream()
				.filter(item -> item.getName().equals(shoppingListItem.getName()))
				.findFirst()
				.ifPresent(item -> item.setSelected(shoppingListItem.isSelected()));
	}
}
