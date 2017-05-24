package household.shoppinglist;

import static household.shoppinglist.ShoppingListTOAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;

public class ShoppingListMapperTest {
	
	private ShoppingListMapper shoppingListMapper;

	@Test
	public void testMap_toShoppingListTO() throws Exception {
		shoppingListMapper = new ShoppingListMapper(new ShoppingListItemMapper());
		
		List<ShoppingListItem> shoppingListItems = asList(new ShoppingListItem("item1", true), new ShoppingListItem("item2", false));
		ShoppingList shoppingList = new ShoppingList(shoppingListItems);
		
		ShoppingListDTO result = shoppingListMapper.map(shoppingList);
		
		assertThat(result)
		        .hasSize(2)
		        .shoppingListItem(0, itemAssert -> itemAssert.hasName("item1").isSelected())
		        .shoppingListItem(1, itemAssert -> itemAssert.hasName("item2").isDeselected());
	}

}
