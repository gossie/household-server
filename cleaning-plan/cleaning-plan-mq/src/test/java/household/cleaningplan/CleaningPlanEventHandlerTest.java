package household.cleaningplan;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;
import household.household.Household;

import org.junit.jupiter.api.Test;

public class CleaningPlanEventHandlerTest {

	private CleaningPlanEventHandler cleaningPlanEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var cleaningPlanService = mock(CleaningPlanService.class);
		var household = mock(Household.class);
		when(household.getCleaningPlanId()).thenReturn(2L);

		cleaningPlanEventHandler = new CleaningPlanEventHandler(mock(EventBus.class), cleaningPlanService);
		cleaningPlanEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(household));

		verify(cleaningPlanService).deleteCleaningPlan(2L);
	}
}
