package household.household;

import household.user.InvitationAcceptedEvent;
import household.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.MessageChannel;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class HouseholdEventHandlerTest {

    @Test
    public void testOnInvitationAccepted_noExistingHousehold() throws Exception {
        HouseholdService HouseholdService = mock(HouseholdService.class);

        HouseholdEventHandler HouseholdEventHandler = new HouseholdEventHandler(HouseholdService, mock(MessageChannel.class), mock(MessageChannel.class));
        HouseholdEventHandler.onInvitationAccepted(new InvitationAcceptedEvent(null, null));

        verifyZeroInteractions(HouseholdService);
    }

    @Test
    public void testOnInvitationAccepted_usersLeft() throws Exception {
        HouseholdService HouseholdService = mock(HouseholdService.class);

        HouseholdEventHandler HouseholdEventHandler = new HouseholdEventHandler(HouseholdService, mock(MessageChannel.class), mock(MessageChannel.class));
        HouseholdEventHandler.onInvitationAccepted(new InvitationAcceptedEvent(5L, Collections.singletonList(mock(User.class))));

        verifyZeroInteractions(HouseholdService);
    }

    @Test
    public void testOnInvitationAccepted_noUsersLeft() throws Exception {
        Household household = new Household(5L, 6L, 7L, 8L, 9L);

        HouseholdService HouseholdService = mock(HouseholdService.class);

        HouseholdEventHandler HouseholdEventHandler = new HouseholdEventHandler(HouseholdService, mock(MessageChannel.class), mock(MessageChannel.class));
        HouseholdEventHandler.onInvitationAccepted(new InvitationAcceptedEvent(5L, Collections.emptyList()));

        verify(HouseholdService).deleteHousehold(5L);
    }

}
