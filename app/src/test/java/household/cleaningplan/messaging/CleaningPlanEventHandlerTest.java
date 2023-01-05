package household.cleaningplan.messaging;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.eventbus.EventBus;

import household.cleaningplan.domain.CleaningPlanService;

import household.household.events.HouseholdDeletedEvent;
import org.junit.jupiter.api.Test;

public class CleaningPlanEventHandlerTest {

	private CleaningPlanEventHandler cleaningPlanEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var cleaningPlanService = mock(CleaningPlanService.class);

		cleaningPlanEventHandler = new CleaningPlanEventHandler(mock(EventBus.class), cleaningPlanService);
		cleaningPlanEventHandler.onHouseholdDeleted(new HouseholdDeletedEvent("1L", "2L", "3L", "4L", "5L"));

		verify(cleaningPlanService).deleteCleaningPlan("3L");
	}
}
