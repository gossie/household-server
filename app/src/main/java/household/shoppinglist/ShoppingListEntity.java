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
public class ShoppingListEntity extends AbstractEntity {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ShoppingListItemEntity> shoppingListItems = new ArrayList<>();

	public void clearSelectedItems() {
		shoppingListItems.removeIf(ShoppingListItemEntity::isSelected);
	}

	public void addShoppingListItem(ShoppingListItemEntity shoppingListItem) {
		shoppingListItems.add(shoppingListItem);
	}
	
	public void update(ShoppingListItemEntity shoppingListItem) {
		shoppingListItems.stream()
				.filter(item -> item.getName().equals(shoppingListItem.getName()))
				.findFirst()
				.ifPresent(item -> item.setSelected(shoppingListItem.isSelected()));
	}
}
