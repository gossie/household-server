package household.shoppinglist.rest;

import static household.shoppinglist.domain.ShoppingListItemAssert.assertThat;
import static household.shoppinglist.rest.ShoppingListItemDTOAssert.assertThat;

import org.junit.jupiter.api.Test;

import household.shoppinglist.domain.ShoppingListItem;

public class ShoppingListItemDTOMapperTest {

    private static final String IMAGE = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mP8z/C/HgAGgwJ/lK3Q6wAAAABJRU5ErkJggg==";

	private ShoppingListItemDTOMapper shoppingListItemMapper;

	@Test
	public void testMap_toSelectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItem shoppingListItem = new ShoppingListItem("1L", "item", true, new byte[]{});
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isSelected();
	}

	@Test
	public void testMap_toDeselectedShoppingListItemTO() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItem shoppingListItem = new ShoppingListItem("1L", "item", false, new byte[]{});
		ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isDeselected();
	}

	@Test
	public void testMap_toSelectedShoppingListItem() throws Exception {
		shoppingListItemMapper = new ShoppingListItemDTOMapper();

		ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO(null, "item", true, IMAGE);
		ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

		assertThat(result).hasName("item").isSelected();
	}

    @Test
    public void testMap_toDeselectedShoppingListItem() throws Exception {
        shoppingListItemMapper = new ShoppingListItemDTOMapper();

        ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO(null, "item", false, IMAGE);
        ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

        assertThat(result).hasName("item").isDeselected();
    }

    @Test
    public void testMap_toDeselectedShoppingListItem_withoutImage() throws Exception {
        shoppingListItemMapper = new ShoppingListItemDTOMapper();

        ShoppingListItemDTO shoppingListItem = new ShoppingListItemDTO(null, "item", false, null);
        ShoppingListItem result = shoppingListItemMapper.map(shoppingListItem);

        assertThat(result)
            .hasName("item")
            .hasNoImage()
            .isDeselected();
    }

    @Test
    public void testMap_toDeselectedShoppingListItemDTO_withoutImage() throws Exception {
        shoppingListItemMapper = new ShoppingListItemDTOMapper();

        ShoppingListItem shoppingListItem = new ShoppingListItem(null, "item", false, null);
        ShoppingListItemDTO result = shoppingListItemMapper.map(shoppingListItem);

        assertThat(result)
            .hasName("item")
            .imageFieldIsEmpty()
            .isDeselected();
    }

}
