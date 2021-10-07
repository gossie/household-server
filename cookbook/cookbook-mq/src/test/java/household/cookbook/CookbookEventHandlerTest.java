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
		var cookbookService = mock(CookbookService.class);
		var household = mock(Household.class);
		when(household.getId()).thenReturn(1L);
		when(household.getShoppingListId()).thenReturn(2L);
		when(household.getCleaningPlanId()).thenReturn(3L);
		when(household.getFoodPlanId()).thenReturn(4L);
		when(household.getCookbookId()).thenReturn(5L);

		shoppingListEventHandler = new CookbookEventHandler(mock(EventBus.class), cookbookService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(1L, 2L, 3L, 4L, 5L));

		verify(cookbookService).deleteCookbook(5L);
	}
}
