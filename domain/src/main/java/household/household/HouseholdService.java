package household.household;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.user.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseholdService {

    private final EventBus eventBus;
	private final HouseholdRepository householdRepository;

    public void init() {
        eventBus.register(this);
    }

	public Household getHousehold(Long householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(Long shoppingListId, Long cleaningPlanId, Long foodPlanId, Long cookbookId) {
		return householdRepository.saveHousehold(new Household(null, shoppingListId, cleaningPlanId, foodPlanId, cookbookId));
	}

	private void deleteHousehold(Long householdId) {
        Household household = householdRepository.determineHousehold(householdId);
        householdRepository.deleteHousehold(householdId);
        eventBus.post(new HouseholdDeletedEvent(household));
    }

	@Subscribe
    public void onInvitationAccepted(InvitationAcceptedEvent event) {
	    event.getOldHouseholdId().ifPresent(oldHouseholdId -> {
	        if(event.getLeftUsers().isEmpty()) {
                deleteHousehold(oldHouseholdId);
            }
        });
    }
}
