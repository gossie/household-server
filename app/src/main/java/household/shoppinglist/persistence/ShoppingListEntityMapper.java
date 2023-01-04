package household.shoppinglist.persistence;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListEntityMapper {

	private final ShoppingListGroupEntityMapper shoppingListGroupMapper;

	ShoppingList map(ShoppingListEntity shoppingList) {
		List<ShoppingListGroup> groups = shoppingList.getShoppingListGroups().stream()
				.map(shoppingListGroupMapper::map)
				.collect(Collectors.toList());

		return new ShoppingList(shoppingList.getId(), groups);
	}

	ShoppingListEntity map(ShoppingList shoppingList) {
		List<ShoppingListGroupEntity> groups = shoppingList.getShoppingListGroups().stream()
				.map(shoppingListGroupMapper::map)
				.collect(Collectors.toList());

		return new ShoppingListEntity(shoppingList.getId(), groups);
	}
}
