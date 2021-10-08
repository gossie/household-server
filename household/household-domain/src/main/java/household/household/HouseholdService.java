package household.household;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	private final List<HouseholdObserver> householdObservers;

	public Household getHousehold(Long householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(Long shoppingListId, Long cleaningPlanId, Long foodPlanId, Long cookbookId) {
		return householdRepository.saveHousehold(new Household(null, shoppingListId, cleaningPlanId, foodPlanId, cookbookId));
	}

	public void deleteHousehold(Long householdId) {
        Household household = householdRepository.determineHousehold(householdId);
        householdRepository.deleteHousehold(householdId);
        householdObservers.forEach(observer -> observer.onHouseholdDeletion(
        		new HouseholdDeletedEvent(
        				household.id(),
        				household.shoppingListId(),
        				household.cleaningPlanId(),
        				household.foodPlanId(),
        				household.cookbookId()
        		)
        ));
    }
}
