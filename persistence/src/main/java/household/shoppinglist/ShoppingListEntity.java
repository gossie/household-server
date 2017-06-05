package household.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
class ShoppingListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ShoppingListItemEntity> shoppingListItems = new ArrayList<>();

	void clearSelectedItems() {
		shoppingListItems.removeIf(ShoppingListItemEntity::isSelected);
	}

	void addShoppingListItem(ShoppingListItemEntity shoppingListItem) {
		shoppingListItems.add(shoppingListItem);
	}
	
	void update(ShoppingListItemEntity shoppingListItem) {
		shoppingListItems.stream()
				.filter(item -> item.getName().equals(shoppingListItem.getName()))
				.findFirst()
				.ifPresent(item -> item.setSelected(shoppingListItem.isSelected()));
	}
}
