package household.shoppinglist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;
import household.household.Household;

import org.junit.jupiter.api.Test;

public class ShoppingListEventHandlerTest {

	private ShoppingListEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var shoppingListService = mock(ShoppingListService.class);
		var household = mock(Household.class);
		when(household.getShoppingListId()).thenReturn(2L);

		shoppingListEventHandler = new ShoppingListEventHandler(mock(EventBus.class), shoppingListService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(household));

		verify(shoppingListService).deleteShoppingList(2L);
	}
}
