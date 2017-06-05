package household.shoppinglist;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListItemMapper {

	ShoppingListItemEntity map(ShoppingListItem shoppingListItem) {
		return new ShoppingListItemEntity(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected());
	}
	
	ShoppingListItem map(ShoppingListItemEntity shoppingListItem) {
		return new ShoppingListItem(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected());
	}
}
