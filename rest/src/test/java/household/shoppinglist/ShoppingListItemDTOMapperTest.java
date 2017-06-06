package household.shoppinglist;

import static household.shoppinglist.ShoppingListItemAssert.assertThat;
import static household.shoppinglist.ShoppingListItemTOAssert.assertThat;

import org.junit.Test;

public class ShoppingListItemDTOMapperTest {

	private ShoppingListItemDTOMapper shoppingListItemMapper;
	
	@Test
	public void testMap_toSelectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();
		
		ShoppingListItem shoppingListItem = new ShoppingListItem(1L, "item", true);
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isSelected();
	}
	
	@Test
	public void testMap_toDeselectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();
		
		ShoppingListItem shoppingListItem = new ShoppingListItem(1L, "item", false);
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isDeselected();
	}

	@Test
	public void testMap_toSelectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();
		
		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO("item", true);
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isSelected();
	}
	
	@Test
	public void testMap_toDeselectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();
		
		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO("item", false);
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);
		
		assertThat(result).hasName("item").isDeselected();
	}

}
