package household.cookbook;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;
import household.household.Household;

import org.junit.jupiter.api.Test;

public class CookbookEventHandlerTest {

	private CookbookEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var shoppingListService = mock(CookbookService.class);
		var household = mock(Household.class);
		when(household.getShoppingListId()).thenReturn(2L);

		shoppingListEventHandler = new CookbookEventHandler(mock(EventBus.class), shoppingListService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(household));

		verify(shoppingListService).deleteCookbook(2L);
	}
}
