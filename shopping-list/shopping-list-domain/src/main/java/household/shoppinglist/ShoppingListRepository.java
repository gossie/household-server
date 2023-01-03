package household.shoppinglist;

public interface ShoppingListRepository {

	ShoppingList determineShoppingList(String shoppingListId);

	ShoppingList saveShoppingList(ShoppingList shoppingList);

//	ShoppingList createShoppingList();

    void deleteShoppingList(String shoppingListId);

}
