package household.cleaningplan;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class CleaningPlanEventHandlerTest {

	private CleaningPlanEventHandler cleaningPlanEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var cleaningPlanService = mock(CleaningPlanService.class);

		cleaningPlanEventHandler = new CleaningPlanEventHandler(cleaningPlanService);
		cleaningPlanEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent(2L));

		verify(cleaningPlanService).deleteCleaningPlan(2L);
	}
}
