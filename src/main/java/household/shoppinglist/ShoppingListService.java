package household.shoppinglist;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShoppingListService {

	private final ShoppingListRepository shoppingListRepository;
	
	public ShoppingList getShoppingList(Long id) {
		return shoppingListRepository.findOne(id);
	}
	
	public ShoppingList removedSelectedItemsFromShoppingList(Long id) {
		ShoppingList shoppingList = shoppingListRepository.findOne(id);
		shoppingList.clearSelectedItems();
		return shoppingListRepository.save(shoppingList);
	}

	public ShoppingList addShoppingListItems(Long id, List<ShoppingListItem> entities) {
		ShoppingList shoppingList = shoppingListRepository.findOne(id);
		entities.forEach(shoppingList::addShoppingListItem);
		return shoppingListRepository.save(shoppingList);
	}

	public ShoppingList update(Long id, ShoppingListItem shoppingListItem) {
		ShoppingList shoppingList = shoppingListRepository.findOne(id);
		shoppingList.update(shoppingListItem);
		return shoppingListRepository.save(shoppingList);
	}
}
