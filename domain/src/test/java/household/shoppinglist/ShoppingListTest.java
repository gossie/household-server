package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShoppingListTest {

	@Test
	public void testClearSelectedItems() throws Exception {
		List<ShoppingListItem> items = new ArrayList<>();
		items.add(new ShoppingListItem(1L, "one", false));
		items.add(new ShoppingListItem(2L, "two", true));
		items.add(new ShoppingListItem(3L, "three", false));
		items.add(new ShoppingListItem(4L, "four", true));
		
		ShoppingList shoppingList = new ShoppingList(5L, items);
		
		shoppingList.clearSelectedItems();
		
		assertThat(shoppingList)
		    .hasSize(2)
		    .shoppingListItem(0, el -> el.hasName("one").isDeselected())
		    .shoppingListItem(1, el -> el.hasName("three").isDeselected());
	}

	@Test
	public void testUpdate() throws Exception {
		List<ShoppingListItem> items = new ArrayList<>();
		items.add(new ShoppingListItem(1L, "one", false));
		items.add(new ShoppingListItem(2L, "two", false));
		ShoppingList shoppingList = new ShoppingList(2L, items);
		
		shoppingList.update(new ShoppingListItem(2L, "two", true));
		assertThat(shoppingList)
		    .hasSize(2)
		    .shoppingListItem(0, el -> el.hasName("one").isDeselected())
		    .shoppingListItem(1, el -> el.hasName("two").isSelected());
		
		shoppingList.update(new ShoppingListItem(2L, "two", false));
		assertThat(shoppingList)
	        .hasSize(2)
	        .shoppingListItem(0, el -> el.hasName("one").isDeselected())
	        .shoppingListItem(1, el -> el.hasName("two").isDeselected());
	}

}
