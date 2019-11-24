package household.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class UserEventHandlerTest {

	private UserEventHandler userEventHandler;

	@Test
	public void testOnHouseholdDeleted() throws Exception {
		var userService = mock(UserService.class);

		userEventHandler = new UserEventHandler(userService);
		userEventHandler.onHouseholdDeletion(new HouseholdDeletedEvent(2L));

		verify(userService).removeHouseholdFromUsers(2L);
	}
}
