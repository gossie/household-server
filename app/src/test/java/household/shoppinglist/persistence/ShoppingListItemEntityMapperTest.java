package household.shoppinglist.persistence;

import static household.shoppinglist.domain.ShoppingListItemAssert.assertThat;
import static household.shoppinglist.persistence.ShoppingListItemEntityAssert.assertThat;

import org.junit.jupiter.api.Test;

import household.shoppinglist.domain.ShoppingListItem;

public class ShoppingListItemEntityMapperTest {

	private ShoppingListItemEntityMapper shoppingListItemMapper;

	@Test
	public void testMap_toSelectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemEntityMapper();

		ShoppingListItemEntity shoppingListItem = new ShoppingListItemEntity("1L", "item", true, "image".getBytes());
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result)
            .hasName("item")
            .hasImage("image".getBytes())
            .isSelected();
	}

	@Test
	public void testMap_toDeselectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemEntityMapper();

		ShoppingListItemEntity shoppingListItem = new ShoppingListItemEntity("1L", "item", false, "image".getBytes());
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result)
            .hasName("item")
            .hasImage("image".getBytes())
            .isDeselected();
	}

	@Test
	public void testMap_toSelectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemEntityMapper();

		ShoppingListItem shoppingListItem = new ShoppingListItem("1L", "item", true, "image".getBytes());
		ShoppingListItemEntity result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result)
            .hasName("item")
            .hasImage("image".getBytes())
            .isSelected();
	}

	@Test
	public void testMap_toDeselectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemEntityMapper();

		ShoppingListItem shoppingListItem = new ShoppingListItem("1L", "item", false, "image".getBytes());
		ShoppingListItemEntity result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result)
            .hasName("item")
            .hasImage("image".getBytes())
            .isDeselected();
	}

}
