package household.shoppinglist.persistence;

import household.shoppinglist.domain.ShoppingListItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListItemEntityMapper {

	ShoppingListItemEntity map(ShoppingListItem shoppingListItem) {
		return new ShoppingListItemEntity(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected(), shoppingListItem.getImage());
	}

	ShoppingListItem map(ShoppingListItemEntity shoppingListItem) {
		return new ShoppingListItem(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected(), shoppingListItem.getImage());
	}
}
