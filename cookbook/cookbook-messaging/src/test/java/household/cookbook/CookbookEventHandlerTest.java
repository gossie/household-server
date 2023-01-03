package household.cookbook;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;

import org.junit.jupiter.api.Test;

public class CookbookEventHandlerTest {

	private CookbookEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var cookbookService = mock(CookbookService.class);

		shoppingListEventHandler = new CookbookEventHandler(mock(EventBus.class), cookbookService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent("1L", "2L", "3L", "4L", "5L"));

		verify(cookbookService).deleteCookbook("5L");
	}
}
