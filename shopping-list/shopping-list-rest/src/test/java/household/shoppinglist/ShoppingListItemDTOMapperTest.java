package household.shoppinglist;

import static household.shoppinglist.ShoppingListItemAssert.assertThat;
import static household.shoppinglist.ShoppingListItemDTOAssert.assertThat;

import org.junit.jupiter.api.Test;

public class ShoppingListItemDTOMapperTest {

	private ShoppingListItemDTOMapper shoppingListItemMapper;

	@Test
	public void testMap_toSelectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItem shoppingListItem = new ShoppingListItem(1L, "item", true, new byte[]{});
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isSelected();
	}

	@Test
	public void testMap_toDeselectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItem shoppingListItem = new ShoppingListItem(1L, "item", false, new byte[]{});
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isDeselected();
	}

	@Test
	public void testMap_toSelectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO(null, "item", true, "SU1BR0U=");
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isSelected();
	}

	@Test
	public void testMap_toDeselectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO(null, "item", false, "SU1BR0U=");
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isDeselected();
	}

}
