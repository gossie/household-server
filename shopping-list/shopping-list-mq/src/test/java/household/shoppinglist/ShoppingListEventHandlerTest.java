package household.shoppinglist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;

import org.junit.jupiter.api.Test;

public class ShoppingListEventHandlerTest {

	private ShoppingListEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var shoppingListService = mock(ShoppingListService.class);

		shoppingListEventHandler = new ShoppingListEventHandler(mock(EventBus.class), shoppingListService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(1L, 2L, 3L, 4L, 5L));

		verify(shoppingListService).deleteShoppingList(2L);
	}
}
