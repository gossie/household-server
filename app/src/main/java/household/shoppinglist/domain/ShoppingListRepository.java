package household.shoppinglist.domain;

public interface ShoppingListRepository {

	ShoppingList determineShoppingList(String shoppingListId);

	ShoppingList createShoppingList(ShoppingList shoppingList);

	ShoppingList saveShoppingList(ShoppingList shoppingList);

    void deleteShoppingList(String shoppingListId);

}
