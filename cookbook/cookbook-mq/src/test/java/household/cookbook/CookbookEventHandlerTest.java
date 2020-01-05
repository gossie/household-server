package household.cookbook;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class CookbookEventHandlerTest {

	private CookbookEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var shoppingListService = mock(CookbookService.class);

		shoppingListEventHandler = new CookbookEventHandler(shoppingListService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(2L));

		verify(shoppingListService).deleteCookbook(2L);
	}
}
