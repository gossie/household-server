package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;

public class ShoppingListEntityMapperTest {
	
	private ShoppingListEntityMapper shoppingListMapper;

	@Test
	public void testMap_toShoppingListTO() throws Exception {
		shoppingListMapper = new ShoppingListEntityMapper(new ShoppingListGroupEntityMapper(new ShoppingListItemEntityMapper()));
		
		List<ShoppingListItemEntity> shoppingListItems = asList(new ShoppingListItemEntity(1L, "item1", true), new ShoppingListItemEntity(2L, "item2", false));
		List<ShoppingListGroupEntity> shoppingListGroups = asList(new ShoppingListGroupEntity(null, "group", shoppingListItems));
		ShoppingListEntity shoppingList = new ShoppingListEntity(3L, shoppingListGroups);
		
		ShoppingList result = shoppingListMapper.map(shoppingList);
		
		assertThat(result)
		        .hasSize(1)
		        .shoppingListGroup(0, group -> group
		                .hasName("group")
		                .hasSize(2)
		                .shoppingListItem(0, itemAssert -> itemAssert.hasName("item1").isSelected())
		                .shoppingListItem(1, itemAssert -> itemAssert.hasName("item2").isDeselected()));
	}

}
