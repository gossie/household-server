package household.shoppinglist;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListItemMapper {

	public ShoppingListItemEntity map(ShoppingListItem shoppingListItem) {
		return new ShoppingListItemEntity(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected());
	}
	
	public ShoppingListItem map(ShoppingListItemEntity shoppingListItem) {
		return new ShoppingListItem(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected());
	}
}
