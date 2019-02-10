package household.household;

import static household.household.HouseholdAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.eventbus.EventBus;
import household.user.InvitationAcceptedEvent;
import household.user.User;

public class HouseholdServiceTest {

	private HouseholdService householdService;

	@Test
	public void testGetHousehold() throws Exception {
		Household expected = mock(Household.class);

		HouseholdRepository householdRepository = mock(HouseholdRepository.class);
		when(householdRepository.determineHousehold(7L)).thenReturn(expected);

		householdService = new HouseholdService(mock(EventBus.class), householdRepository);
		Household result = householdService.getHousehold(7L);

		assertThat(result).isSameAs(expected);
	}

	@Test
	@Ignore
	public void testCreateHousehold() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

    @Test
    public void testOnInvitationAccepted_noExistingHousehold() throws Exception {
        EventBus eventBus = mock(EventBus.class);
        HouseholdRepository householdRepository = mock(HouseholdRepository.class);

        HouseholdService householdService = new HouseholdService(eventBus, householdRepository);
        householdService.onInvitationAccepted(new InvitationAcceptedEvent(null, null));

        verifyZeroInteractions(eventBus, householdRepository);
    }

    @Test
    public void testOnInvitationAccepted_usersLeft() throws Exception {
        EventBus eventBus = mock(EventBus.class);

        HouseholdRepository householdRepository = mock(HouseholdRepository.class);

        HouseholdService householdService = new HouseholdService(eventBus, householdRepository);
        householdService.onInvitationAccepted(new InvitationAcceptedEvent(5L, Collections.singletonList(mock(User.class))));

        verifyZeroInteractions(eventBus, householdRepository);
    }

    @Test
    public void testOnInvitationAccepted_noUsersLeft() throws Exception {
        EventBus eventBus = mock(EventBus.class);

        Household household = new Household(5L, 6L, 7L, 8L, 9L);

        HouseholdRepository householdRepository = mock(HouseholdRepository.class);
        when(householdRepository.determineHousehold(5L)).thenReturn(household);

        HouseholdService householdService = new HouseholdService(eventBus, householdRepository);
        householdService.onInvitationAccepted(new InvitationAcceptedEvent(5L, Collections.emptyList()));

        verify(householdRepository).deleteHousehold(5L);
        verify(eventBus).post(new HouseholdDeletedEvent(household));
    }

}
