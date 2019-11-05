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
		when(household.getId()).thenReturn(2L);

		userEventHandler = new UserEventHandler(mock(EventBus.class), userService);
		userEventHandler.onHouseholdDeletion(new HouseholdDeletedEvent(household));

		verify(userService).removeHouseholdFromUsers(2L);
	}
}
