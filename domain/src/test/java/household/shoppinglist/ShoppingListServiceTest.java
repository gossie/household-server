package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ShoppingListServiceTest {

	private ShoppingListService shoppingListService;

	@Test
	public void testGetShoppingList() throws Exception {
		ShoppingList expectedShoppingList = new ShoppingList(1L);
		
		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.determineShoppingList(1L)).thenReturn(expectedShoppingList);
		
		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList actualShoppingList = shoppingListService.getShoppingList(1L);
		
		assertThat(actualShoppingList).isSameAs(expectedShoppingList);
	}

    @Test
    public void testAddShoppingListGroup() throws Exception {
        List<ShoppingListGroup> groups = asList(new ShoppingListGroup(3L, "group", Collections.emptyList()));
        ShoppingList shoppingList = new ShoppingList(1L, groups);
        
        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList(1L)).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);
        
        shoppingListService = new ShoppingListService(shoppingListRepository);
        ShoppingList result = shoppingListService.addShoppingListGroup(1L, new ShoppingListGroup(null, "group2", Collections.emptyList()));

        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group.hasName("group").hasSize(0))
            .shoppingListGroup(1, group -> group.hasName("group2").hasSize(0));
    }

	@Test
	public void testAddShoppingListItems() throws Exception {
	    List<ShoppingListGroup> groups = asList(new ShoppingListGroup(3L, "group", Collections.emptyList()));
		ShoppingList shoppingList = new ShoppingList(1L, groups);
		
		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.determineShoppingList(1L)).thenReturn(shoppingList);
		when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);
		
		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList result = shoppingListService.addShoppingListItems(1L, 3L, asList(new ShoppingListItem(2L, "new", false)));

		assertThat(result)
		    .hasSize(1)
		    .shoppingListGroup(0, group -> group.hasName("group").hasSize(1).shoppingListItem(0, el -> el.hasName("new").isDeselected()));
	}

    @Test
        public void testRemoveSelectedItemsFromShoppingListGroup() throws Exception {
            List<ShoppingListItem> items1 = new ArrayList<>();
            items1.add(new ShoppingListItem(1L, "one", false));
            items1.add(new ShoppingListItem(2L, "two", true));
            
            List<ShoppingListItem> items2 = new ArrayList<>();
            items2.add(new ShoppingListItem(3L, "three", false));
            items2.add(new ShoppingListItem(4L, "four", true));
            
            ShoppingListGroup group1 = new ShoppingListGroup(8L, "group1", items1);
            ShoppingListGroup group2 = new ShoppingListGroup(9L, "group2", items2);
            
            ShoppingList shoppingList = new ShoppingList(5L, asList(group1, group2));
            
            ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
            when(shoppingListRepository.determineShoppingList(5L)).thenReturn(shoppingList);
            when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);
            
            shoppingListService = new ShoppingListService(shoppingListRepository);
            ShoppingList result = shoppingListService.removeSelectedItemsFromShoppingListGroup(5L, 8L);
            
            assertThat(result)
                .hasSize(2)
                .shoppingListGroup(0, group -> group
                        .hasName("group1")
                        .hasSize(1)
                        .shoppingListItem(0, item -> item.hasName("one").isDeselected()))
                .shoppingListGroup(1, group -> group
                        .hasName("group2")
                        .hasSize(2)
                        .shoppingListItem(0, item -> item.hasName("three").isDeselected())
                        .shoppingListItem(1, item -> item.hasName("four").isSelected()));
        }

    @Test
    public void testRemoveAllSelectedItems() throws Exception {
        List<ShoppingListItem> items1 = new ArrayList<>();
        items1.add(new ShoppingListItem(1L, "one", false));
        items1.add(new ShoppingListItem(2L, "two", true));
        
        List<ShoppingListItem> items2 = new ArrayList<>();
        items2.add(new ShoppingListItem(3L, "three", false));
        items2.add(new ShoppingListItem(4L, "four", true));
        
        ShoppingListGroup group1 = new ShoppingListGroup(8L, "group1", items1);
        ShoppingListGroup group2 = new ShoppingListGroup(9L, "group2", items2);
        
        ShoppingList shoppingList = new ShoppingList(5L, asList(group1, group2));
        
        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList(5L)).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);
        
        shoppingListService = new ShoppingListService(shoppingListRepository);
        ShoppingList result = shoppingListService.removeAllSelectedItems(5L);
        
        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group
                    .hasName("group1")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item.hasName("one").isDeselected()))
            .shoppingListGroup(1, group -> group
                    .hasName("group2")
                    .hasSize(1)
                    .shoppingListItem(0, item -> item.hasName("three").isDeselected()));
    }

	@Test
	public void testToggleItem() throws Exception {
	    List<ShoppingListItem> items1 = new ArrayList<>();
        items1.add(new ShoppingListItem(1L, "one", false));
        items1.add(new ShoppingListItem(2L, "two", true));
        
        List<ShoppingListItem> items2 = new ArrayList<>();
        items2.add(new ShoppingListItem(3L, "three", false));
        items2.add(new ShoppingListItem(4L, "four", true));
        
        ShoppingListGroup group1 = new ShoppingListGroup(8L, "group1", items1);
        ShoppingListGroup group2 = new ShoppingListGroup(9L, "group2", items2);
        
        ShoppingList shoppingList = new ShoppingList(5L, asList(group1, group2));
        
        ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
        when(shoppingListRepository.determineShoppingList(5L)).thenReturn(shoppingList);
        when(shoppingListRepository.saveShoppingList(shoppingList)).thenReturn(shoppingList);
        
        shoppingListService = new ShoppingListService(shoppingListRepository);
        
        ShoppingList result = shoppingListService.toggleItem(5L, 9L, 3L);
        
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
                    .shoppingListItem(0, item -> item.hasName("three").isSelected())
                    .shoppingListItem(1, item -> item.hasName("four").isSelected()));
        
        result = shoppingListService.toggleItem(5L, 8L, 2L);
        
        assertThat(result)
            .hasSize(2)
            .shoppingListGroup(0, group -> group
                    .hasName("group1")
                    .hasSize(2)
                    .shoppingListItem(0, item -> item.hasName("one").isDeselected())
                    .shoppingListItem(1, item -> item.hasName("two").isDeselected()))
            .shoppingListGroup(1, group -> group
                    .hasName("group2")
                    .hasSize(2)
                    .shoppingListItem(0, item -> item.hasName("three").isSelected())
                    .shoppingListItem(1, item -> item.hasName("four").isSelected()));
	}
}
