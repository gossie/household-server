package household.shoppinglist;

import static household.shoppinglist.ShoppingListItemAssert.assertThat;
import static household.shoppinglist.ShoppingListItemTOAssert.assertThat;

import org.junit.Test;

public class ShoppingListItemMapperTest {

	private ShoppingListItemMapper shoppingListItemMapper;
	
	@Test
	public void testMap_toSelectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemMapper();
		
		ShoppingListItem shoppingListItem = new ShoppingListItem("item", true);
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isSelected();
	}
	
	@Test
	public void testMap_toDeselectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemMapper();
		
		ShoppingListItem shoppingListItem = new ShoppingListItem("item", false);
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isDeselected();
	}

	@Test
	public void testMap_toSelectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemMapper();
		
		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO("item", true);
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isSelected();
	}
	
	@Test
	public void testMap_toDeselectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemMapper();
		
		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO("item", false);
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isDeselected();
	}

}
