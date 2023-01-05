package household.shoppinglist.domain;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingListService {

	private final ShoppingListRepository shoppingListRepository;

	public ShoppingList getShoppingList(String shoppingListId) {
		return shoppingListRepository.determineShoppingList(shoppingListId);
	}

    public ShoppingList removeAllSelectedItems(String shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.clearAllSelectedItems();
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList removeSelectedItemsFromShoppingListGroup(String shoppingListId, String shoppingListGroupId) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		shoppingList.clearSelectedItemsFromShoppingListGroup(shoppingListGroupId);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

    public ShoppingList addShoppingListGroup(String shoppingListId, ShoppingListGroup group) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.addShoppingListGroup(group);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

    public ShoppingList deleteShoppingListGroup(String shoppingListId, String groupId) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.deleteShoppingListGroup(groupId);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList addShoppingListItems(String shoppingListId, String shoppingListGroupId, List<ShoppingListItem> entities) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		entities.forEach(item -> shoppingList.addShoppingListItem(shoppingListGroupId, item));
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

	public ShoppingList toggleItem(String shoppingListId, String shoppingListGroupId, String shoppingListItemId) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		shoppingList.toggleItem(shoppingListGroupId, shoppingListItemId);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

    public ShoppingList toggleShoppingListGroup(String shoppingListId, String shoppingListGroupId) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.toggleGroup(shoppingListGroupId);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList createShoppingList() {
		return shoppingListRepository.createShoppingList(new ShoppingList(null));
	}

    public void deleteShoppingList(String shoppingListId) {
        shoppingListRepository.deleteShoppingList(shoppingListId);
    }

    public ShoppingList editItem(String shoppingListId, String shoppingListGroupId, String shoppingListItemId, ShoppingListItem item) {
        var shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.editItem(shoppingListGroupId, shoppingListItemId, item);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }
}
