package household.household;

import com.google.common.eventbus.EventBus;
import household.user.InvitationAcceptedEvent;
import household.user.User;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class HouseholdEventHandlerTest {

    @Test
    public void testOnInvitationAccepted_noExistingHousehold() throws Exception {
        HouseholdService HouseholdService = mock(HouseholdService.class);

        HouseholdEventHandler HouseholdEventHandler = new HouseholdEventHandler(mock(EventBus.class), HouseholdService);
        HouseholdEventHandler.onInvitationAccepted(new InvitationAcceptedEvent(null, null));

        verifyZeroInteractions(HouseholdService);
    }

    @Test
    public void testOnInvitationAccepted_usersLeft() throws Exception {
        HouseholdService HouseholdService = mock(HouseholdService.class);

        HouseholdEventHandler HouseholdEventHandler = new HouseholdEventHandler(mock(EventBus.class), HouseholdService);
        HouseholdEventHandler.onInvitationAccepted(new InvitationAcceptedEvent(5L, Collections.singletonList(mock(User.class))));

        verifyZeroInteractions(HouseholdService);
    }

    @Test
    public void testOnInvitationAccepted_noUsersLeft() throws Exception {
        Household household = new Household(5L, 6L, 7L, 8L, 9L);

        HouseholdService HouseholdService = mock(HouseholdService.class);

        HouseholdEventHandler HouseholdEventHandler = new HouseholdEventHandler(mock(EventBus.class), HouseholdService);
        HouseholdEventHandler.onInvitationAccepted(new InvitationAcceptedEvent(5L, Collections.emptyList()));

        verify(HouseholdService).deleteHousehold(5L);
    }

}
