package household.user.messaging;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.eventbus.EventBus;

import household.household.HouseholdDeletedEvent;

import org.junit.jupiter.api.Test;

public class UserEventHandlerTest {

	private UserEventHandler userEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var userService = mock(UserService.class);

		userEventHandler = new UserEventHandler(mock(EventBus.class), userService);
		userEventHandler.onHouseholdDeletion(new HouseholdDeletedEvent("1L", "2L", "3L", "4L", "5L"));

		verify(userService).removeHouseholdFromUsers("1L");
	}
}
