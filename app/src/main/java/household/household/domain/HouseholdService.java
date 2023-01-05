package household.household.domain;

import household.household.events.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	private final List<HouseholdObserver> householdObservers;

	public Household getHousehold(String householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(String shoppingListId, String cleaningPlanId, String foodPlanId, String cookbookId) {
		return householdRepository.createHousehold(new Household(null, shoppingListId, cleaningPlanId, foodPlanId, cookbookId));
	}

	public void deleteHousehold(String householdId) {
        Household household = householdRepository.determineHousehold(householdId);
        householdRepository.deleteHousehold(householdId);
        householdObservers.forEach(observer -> observer.onHouseholdDeletion(
        		new HouseholdDeletedEvent(
        				household.getId(),
        				household.getShoppingListId(),
        				household.getCleaningPlanId(),
        				household.getFoodPlanId(),
        				household.getCookbookId()
        		)
        ));
    }
}
