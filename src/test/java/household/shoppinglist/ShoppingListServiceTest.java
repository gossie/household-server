package household.shoppinglist;

import static household.shoppinglist.ShoppingListAssert.assertThat;
import static household.shoppinglist.ShoppingListItemAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ShoppingListServiceTest {

	private ShoppingListService shoppingListService;

	@Test
	public void testGetShoppingList() throws Exception {
		ShoppingList expectedShoppingList = new ShoppingList();
		
		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.findOne(1L)).thenReturn(expectedShoppingList);
		
		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList actualShoppingList = shoppingListService.getShoppingList(1L);
		
		assertThat(actualShoppingList).isSameAs(expectedShoppingList);
	}

	@Test
		public void testAddShoppingListItems() throws Exception {
			ShoppingList shoppingList = new ShoppingList();
			
			ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
			when(shoppingListRepository.findOne(1L)).thenReturn(shoppingList);
			when(shoppingListRepository.save(shoppingList)).thenReturn(shoppingList);
			
			shoppingListService = new ShoppingListService(shoppingListRepository);
			ShoppingList result = shoppingListService.addShoppingListItems(1L, asList(new ShoppingListItem("new", false)));
	
			assertThat(result).hasSize(1);
			assertThat(result.getShoppingListItems().get(0)).hasName("new").isDeselected();
		}

	@Test
	public void testRemovedSelectedItemsFromShoppingList() throws Exception {
		List<ShoppingListItem> items = new ArrayList<>();
		items.add(new ShoppingListItem("one", false));
		items.add(new ShoppingListItem("two", true));
		items.add(new ShoppingListItem("three", false));
		items.add(new ShoppingListItem("four", true));
		ShoppingList shoppingList = new ShoppingList(items);
		
		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.findOne(1L)).thenReturn(shoppingList);
		when(shoppingListRepository.save(shoppingList)).thenReturn(shoppingList);
		
		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList result = shoppingListService.removedSelectedItemsFromShoppingList(1L);
		
		assertThat(result).hasSize(2);
		assertThat(result.getShoppingListItems().get(0)).hasName("one").isDeselected();
		assertThat(result.getShoppingListItems().get(1)).hasName("three").isDeselected();
	}

	@Test
	public void testUpdate() throws Exception {
		ShoppingListItem input = mock(ShoppingListItem.class);
		ShoppingList saved = mock(ShoppingList.class);
		
		ShoppingList expectedResult = mock(ShoppingList.class);
		
		ShoppingListRepository shoppingListRepository = mock(ShoppingListRepository.class);
		when(shoppingListRepository.findOne(1L)).thenReturn(saved);
		when(shoppingListRepository.save(saved)).thenReturn(expectedResult);
		
		shoppingListService = new ShoppingListService(shoppingListRepository);
		ShoppingList actualResult = shoppingListService.update(1L, input);
		
		assertThat(actualResult).isSameAs(expectedResult);
		verify(saved).update(input);
	}
}
