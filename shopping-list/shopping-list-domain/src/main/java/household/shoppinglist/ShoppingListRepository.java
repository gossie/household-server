package household.shoppinglist;

public interface ShoppingListRepository {

	ShoppingList determineShoppingList(Long shoppingListId);

	ShoppingList saveShoppingList(ShoppingList shoppingList);

//	ShoppingList createShoppingList();

    void deleteShoppingList(Long shoppingListId);

}
