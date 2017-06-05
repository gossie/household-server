package household.shoppinglist;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListPersistenceMapper {
	
	private final ShoppingListItemMapper shoppingListItemMapper;

	ShoppingList map(ShoppingListEntity shoppingList) {
		List<ShoppingListItem> items = shoppingList.getShoppingListItems().stream()
				.map(shoppingListItemMapper::map)
				.collect(Collectors.toList());
		
		return new ShoppingList(shoppingList.getId(), items);
	}

	ShoppingListEntity map(ShoppingList shoppingList) {
		List<ShoppingListItemEntity> items = shoppingList.getShoppingListItems().stream()
				.map(shoppingListItemMapper::map)
				.collect(Collectors.toList());
		
		return new ShoppingListEntity(shoppingList.getId(), items);
	}
}
