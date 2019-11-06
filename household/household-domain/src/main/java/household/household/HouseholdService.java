package household.household;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	private final List<HouseholdObserver> householdObservers = new ArrayList<>();

	public void addObserver(HouseholdObserver observer) {
	    householdObservers.add(observer);
    }

	public Household getHousehold(Long householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(Long shoppingListId, Long cleaningPlanId, Long foodPlanId, Long cookbookId) {
        Household household = householdRepository.saveHousehold(new Household(null, shoppingListId, cleaningPlanId, foodPlanId, cookbookId));
        householdObservers.forEach(observer -> observer.onHouseholdCreation(household));
        return household;
	}

	public void deleteHousehold(Long householdId) {
        Household household = householdRepository.determineHousehold(householdId);
        householdRepository.deleteHousehold(householdId);
        householdObservers.forEach(observer -> observer.onHouseholdDeletion(household));
    }
}
