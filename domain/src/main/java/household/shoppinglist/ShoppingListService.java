package household.shoppinglist;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoppingListService {

	private final ShoppingListRepository shoppingListRepository;
	
	public ShoppingList getShoppingList(Long shoppingListId) {
		return shoppingListRepository.determineShoppingList(shoppingListId);
	}
	
	public ShoppingList removedSelectedItemsFromShoppingList(Long id) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(id);
		shoppingList.clearSelectedItems();
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

	public ShoppingList addShoppingListItems(Long shoppingListId, List<ShoppingListItem> entities) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		entities.forEach(shoppingList::addShoppingListItem);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

	public ShoppingList update(Long id, ShoppingListItem shoppingListItem) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(id);
		shoppingList.update(shoppingListItem);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}
}
