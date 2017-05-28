package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static household.shoppinglist.ShoppingListItemAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShoppingListEntityTest {

	@Test
	public void testClearSelectedItems() throws Exception {
		List<ShoppingListItemEntity> items = new ArrayList<>();
		items.add(new ShoppingListItemEntity("one", false));
		items.add(new ShoppingListItemEntity("two", true));
		items.add(new ShoppingListItemEntity("three", false));
		items.add(new ShoppingListItemEntity("four", true));
		
		ShoppingListEntity shoppingList = new ShoppingListEntity(items);
		
		shoppingList.clearSelectedItems();
		
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("three").isDeselected();
	}

	@Test
	public void testUpdate() throws Exception {
		List<ShoppingListItemEntity> items = new ArrayList<>();
		items.add(new ShoppingListItemEntity("one", false));
		items.add(new ShoppingListItemEntity("two", false));
		ShoppingListEntity shoppingList = new ShoppingListEntity(items);
		
		shoppingList.update(new ShoppingListItemEntity("two", true));
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("two").isSelected();
		
		shoppingList.update(new ShoppingListItemEntity("two", false));
		assertThat(shoppingList).hasSize(2);
		assertThat(shoppingList.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(shoppingList.getShoppingListItems().get(1)).hasName("two").isDeselected();
	}

}
