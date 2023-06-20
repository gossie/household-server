package household.shoppinglist.domain;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListService.class);

	private final ShoppingListRepository shoppingListRepository;

	public ShoppingList getShoppingList(String shoppingListId) {
        LOGGER.info("Retrieve shopping list {}", shoppingListId);
		return shoppingListRepository.determineShoppingList(shoppingListId);
	}

    public ShoppingList removeAllSelectedItems(String shoppingListId) {
        LOGGER.info("Remove selected items from shopping list {}", shoppingListId);
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.clearAllSelectedItems();
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList removeSelectedItemsFromShoppingListGroup(String shoppingListId, String shoppingListGroupId) {
        LOGGER.info("Remove selected items from shopping list group {} of shopping list {}", shoppingListGroupId, shoppingListId);
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		shoppingList.clearSelectedItemsFromShoppingListGroup(shoppingListGroupId);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

    public ShoppingList addShoppingListGroup(String shoppingListId, ShoppingListGroup group) {
        LOGGER.info("Add shopping list group to shopping list {}", shoppingListId);
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.addShoppingListGroup(group);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

    public ShoppingList deleteShoppingListGroup(String shoppingListId, String groupId) {
        LOGGER.info("Delete shopping list group {} of shopping list {}", groupId, shoppingListId);
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.deleteShoppingListGroup(groupId);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList addShoppingListItems(String shoppingListId, String shoppingListGroupId, List<ShoppingListItem> entities) {
		LOGGER.info("Add shopping list items to shopping list group {} of shopping list {}", shoppingListGroupId, shoppingListId);
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		entities.forEach(item -> shoppingList.addShoppingListItem(shoppingListGroupId, item));
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

	public ShoppingList toggleItem(String shoppingListId, String shoppingListGroupId, String shoppingListItemId) {
        LOGGER.info("Toggle item {} in shopping list group {} of shopping list {}", shoppingListItemId, shoppingListGroupId, shoppingListId);
		ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
		shoppingList.toggleItem(shoppingListGroupId, shoppingListItemId);
		return shoppingListRepository.saveShoppingList(shoppingList);
	}

    public ShoppingList toggleShoppingListGroup(String shoppingListId, String shoppingListGroupId) {
        LOGGER.info("Toggle shopping list group {} of shopping list {}", shoppingListGroupId, shoppingListId);
        ShoppingList shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.toggleGroup(shoppingListGroupId);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }

	public ShoppingList createShoppingList() {
        LOGGER.info("Create new shopping list");
		return shoppingListRepository.createShoppingList(new ShoppingList(null));
	}

    public void deleteShoppingList(String shoppingListId) {
        LOGGER.info("Delete shopping list {}", shoppingListId);
        shoppingListRepository.deleteShoppingList(shoppingListId);
    }

    public ShoppingList editItem(String shoppingListId, String shoppingListGroupId, String shoppingListItemId, ShoppingListItem item) {
        LOGGER.info("Edit item {} in shopping list group {} of shopping list {}", shoppingListItemId, shoppingListGroupId, shoppingListId);
        var shoppingList = shoppingListRepository.determineShoppingList(shoppingListId);
        shoppingList.editItem(shoppingListGroupId, shoppingListItemId, item);
        return shoppingListRepository.saveShoppingList(shoppingList);
    }
}
