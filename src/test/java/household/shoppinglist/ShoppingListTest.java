package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static household.shoppinglist.ShoppingListItemAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShoppingListTest {

	@Test
	public void testClearSelectedItems() throws Exception {
		List<ShoppingListItem> items = new ArrayList<>();
		items.add(new ShoppingListItem("one", false));
		items.add(new ShoppingListItem("two", true));
		items.add(new ShoppingListItem("three", false));
		items.add(new ShoppingListItem("four", true));
		
		ShoppingList shoppingList = new ShoppingList(items);
		
		shoppingList.clearSelectedItems();
		
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("three").isDeselected();
	}

	@Test
	public void testUpdate() throws Exception {
		List<ShoppingListItem> items = new ArrayList<>();
		items.add(new ShoppingListItem("one", false));
		items.add(new ShoppingListItem("two", false));
		ShoppingList shoppingList = new ShoppingList(items);
		
		shoppingList.update(new ShoppingListItem("two", true));
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("two").isSelected();
		
		shoppingList.update(new ShoppingListItem("two", false));
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("two").isDeselected();
	}

}
