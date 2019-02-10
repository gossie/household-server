package household.household;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.cleaningplan.CleaningPlan;
import household.cookbook.Cookbook;
import household.foodplan.FoodPlan;
import household.shoppinglist.ShoppingList;
import household.user.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseholdService {

    private final EventBus eventBus;
	private final HouseholdRepository householdRepository;

	public Household getHousehold(Long householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(ShoppingList shoppingList, CleaningPlan cleaningPlan, FoodPlan foodPlan, Cookbook cookbook) {
		return householdRepository.saveHousehold(new Household(null, shoppingList.getId(), cleaningPlan.getId(), foodPlan.getId(), cookbook.getId()));
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
