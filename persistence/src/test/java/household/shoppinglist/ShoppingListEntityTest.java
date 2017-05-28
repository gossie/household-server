package household.shoppinglist;

import static household.shoppinglist.ShoppingListEntityAssert.assertThat;
import static household.shoppinglist.ShoppingListItemEntityAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// TODO: assertions of nested classes
public class ShoppingListEntityTest {

	@Test
	public void testClearSelectedItems() throws Exception {
		List<ShoppingListItemEntity> items = new ArrayList<>();
		items.add(new ShoppingListItemEntity(1L, "one", false));
		items.add(new ShoppingListItemEntity(2L, "two", true));
		items.add(new ShoppingListItemEntity(3L, "three", false));
		items.add(new ShoppingListItemEntity(4L, "four", true));
		
		ShoppingListEntity shoppingList = new ShoppingListEntity(5L, items);
		
		shoppingList.clearSelectedItems();
		
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("three").isDeselected();
	}

	@Test
	public void testUpdate() throws Exception {
		List<ShoppingListItemEntity> items = new ArrayList<>();
		items.add(new ShoppingListItemEntity(1L, "one", false));
		items.add(new ShoppingListItemEntity(2L, "two", false));
		ShoppingListEntity shoppingList = new ShoppingListEntity(3L, items);
		
		shoppingList.update(new ShoppingListItemEntity(2L, "two", true));
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("two").isSelected();
		
		shoppingList.update(new ShoppingListItemEntity(2L, "two", false));
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("two").isDeselected();
	}

}
