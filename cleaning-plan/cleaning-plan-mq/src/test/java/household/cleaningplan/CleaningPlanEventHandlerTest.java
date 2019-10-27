package household.cleaningplan;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;
import household.household.Household;

import org.junit.jupiter.api.Test;

public class CleaningPlanEventHandlerTest {

	private CleaningPlanEventHandler shoppingListEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var shoppingListService = mock(CleaningPlanService.class);
		var household = mock(Household.class);
		when(household.getCleaningPlanId()).thenReturn(2L);

		shoppingListEventHandler = new CleaningPlanEventHandler(mock(EventBus.class), shoppingListService);
		shoppingListEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(household));

		verify(shoppingListService).deleteCleaningPlan(2L);
	}
}
