package household.shoppinglist;

import java.util.List;

import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoppingListService {

	private final ShoppingListRepository shoppingListRepository;

	public ShoppingList getShoppingList(Long shoppingListId) {
		return shoppingListRepository.determineShoppingList(shoppingListId);
	}

    public ShoppingList removeAllSelectedItems(Long shoppingListId) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.clearAllSelectedItems();
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList removeSelectedItemsFromShoppingListGroup(Long shoppingListId, Long shoppingListGroupId) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		shoppingList.clearSelectedItemsFromShoppingListGroup(shoppingListGroupId);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

    public ShoppingList addShoppingListGroup(Long shoppingListId, ShoppingListGroup group) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.addShoppingListGroup(group);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

    public ShoppingList deleteShoppingListGroup(Long shoppingListId, Long groupId) {
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.deleteShoppingListGroup(groupId);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList addShoppingListItems(Long shoppingListId, Long shoppingListGroupId, List<ShoppingListItem> entities) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		entities.forEach(item -> shoppingList.addShoppingListItem(shoppingListGroupId, item));
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

	public ShoppingList toggleItem(Long shoppingListId, Long shoppingListGroupId, Long shoppingListItemId) {
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		shoppingList.toggleItem(shoppingListGroupId, shoppingListItemId);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

	public ShoppingList createShoppingList() {
		return shoppingListRepository.saveShoppingList(new ShoppingList(null));
	}

    private void deleteCookbook(Long shoppingListId) {
        shoppingListRepository.deleteShoppingList(shoppingListId);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        deleteCookbook(event.getHousehold().getShoppingListId());
    }
}
