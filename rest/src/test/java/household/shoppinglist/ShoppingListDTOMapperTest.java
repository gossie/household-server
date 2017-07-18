package household.shoppinglist;

import static household.shoppinglist.ShoppingListTOAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShoppingListDTOMapperTest {
	
	private ShoppingListDTOMapper shoppingListMapper;

	@Test
	public void testMap_toShoppingListTO() throws Exception {
		shoppingListMapper = new ShoppingListDTOMapper(new ShoppingListGroupDTOMapper(new ShoppingListItemDTOMapper()));
		
		List<ShoppingListItem> items1 = new ArrayList<>();
        items1.add(new ShoppingListItem(1L, "one", false));
        items1.add(new ShoppingListItem(2L, "two", true));
        
        List<ShoppingListItem> items2 = new ArrayList<>();
        items2.add(new ShoppingListItem(3L, "three", false));
        items2.add(new ShoppingListItem(4L, "four", true));
        
        ShoppingListGroup group1 = new ShoppingListGroup(8L, "group1", items1);
        ShoppingListGroup group2 = new ShoppingListGroup(9L, "group2", items2);
        
        ShoppingList shoppingList = new ShoppingList(5L, asList(group1, group2));
		
		ShoppingListDTO result = shoppingListMapper.map(shoppingList);
		
		assertThat(result)
		        .hasSize(2)
		        .shoppingListGroup(0, group -> group
                        .hasName("group1")
                        .hasSize(2)
                        .shoppingListItem(0, item -> item.hasName("one").isDeselected())
                        .shoppingListItem(1, item -> item.hasName("two").isSelected()))
                .shoppingListGroup(1, group -> group
                        .hasName("group2")
                        .hasSize(2)
                        .shoppingListItem(0, item -> item.hasName("three").isDeselected())
                        .shoppingListItem(1, item -> item.hasName("four")));
	}

}
