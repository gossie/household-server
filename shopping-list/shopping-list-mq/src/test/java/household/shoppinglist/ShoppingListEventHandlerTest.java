package household.shoppinglist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class ShoppingListEventHandlerTest {

	private ShoppingListEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var shoppingListService = mock(ShoppingListService.class);

		shoppingListEventHandler = new ShoppingListEventHandler(shoppingListService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(2L));

		verify(shoppingListService).deleteShoppingList(2L);
	}
}
