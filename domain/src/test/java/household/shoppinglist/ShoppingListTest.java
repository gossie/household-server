package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShoppingListTest {

	@Test
	public void testClearSelectedItemsFromShoppingListGroup() throws Exception {
		List<ShoppingListItem> items1 = new ArrayList<>();
		items1.add(new ShoppingListItem(1L, "one", false));
		items1.add(new ShoppingListItem(2L, "two", true));
		
		List<ShoppingListItem> items2 = new ArrayList<>();
		items2.add(new ShoppingListItem(3L, "three", false));
		items2.add(new ShoppingListItem(4L, "four", true));
		
		ShoppingListGroup group1 = new ShoppingListGroup(8L, "group1", items1);
		ShoppingListGroup group2 = new ShoppingListGroup(9L, "group2", items2);
		
		ShoppingList shoppingList = new ShoppingList(5L, asList(group1, group2));
		
		shoppingList.clearAllSelectedItems();
		
		assertThat(shoppingList)
		    .hasSize(2)
		    .shoppingListGroup(0, group -> group.hasName("group1").shoppingListItem(0, item -> item.hasName("one").isDeselected()))
		    .shoppingListGroup(1, group -> group.hasName("group2").shoppingListItem(0, item -> item.hasName("three").isDeselected()));
	}

	@Test
	public void testToggleItem() throws Exception {
		List<ShoppingListItem> items = new ArrayList<>();
		items.add(new ShoppingListItem(1L, "one", false));
		items.add(new ShoppingListItem(2L, "two", false));
		
		List<ShoppingListGroup> groups = asList(new ShoppingListGroup(7L, "group", items));
		
		ShoppingList shoppingList = new ShoppingList(2L, groups);
		
		shoppingList.toggleItem(7L, 2L);
		assertThat(shoppingList)
		    .hasSize(1)
		    .shoppingListGroup(0, group -> group
		            .hasName("group")
        		    .shoppingListItem(0, el -> el.hasName("one").isDeselected())
        		    .shoppingListItem(1, el -> el.hasName("two").isSelected()));
		
		shoppingList.toggleItem(7L, 2L);
		assertThat(shoppingList)
		.hasSize(1)
        .shoppingListGroup(0, group -> group
                .hasName("group")
                .shoppingListItem(0, el -> el.hasName("one").isDeselected())
                .shoppingListItem(1, el -> el.hasName("two").isDeselected()));
	}

}
