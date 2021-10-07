package household.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;
import household.household.Household;

import org.junit.jupiter.api.Test;

public class UserEventHandlerTest {

	private UserEventHandler userEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var userService = mock(UserService.class);
		var household = mock(Household.class);
		when(household.getId()).thenReturn(1L);
		when(household.getShoppingListId()).thenReturn(2L);
		when(household.getCleaningPlanId()).thenReturn(3L);
		when(household.getFoodPlanId()).thenReturn(4L);
		when(household.getCookbookId()).thenReturn(5L);

		userEventHandler = new UserEventHandler(mock(EventBus.class), userService);
		userEventHandler.onHouseholdDeletion(new HouseholdDeletedEvent(1L, 2L, 3L, 4L, 5L));

		verify(userService).removeHouseholdFromUsers(1L);
	}
}
