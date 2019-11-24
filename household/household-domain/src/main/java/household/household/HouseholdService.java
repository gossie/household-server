package household.household;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	private final List<HouseholdServiceObserver> householdServiceObservers = new ArrayList<>();

	void addObserver(HouseholdServiceObserver observer) {
	    householdServiceObservers.add(observer);
    }

	public Household getHousehold(Long householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(Long shoppingListId, Long cleaningPlanId, Long foodPlanId, Long cookbookId) {
        Household household = householdRepository.saveHousehold(new Household(null, shoppingListId, cleaningPlanId, foodPlanId, cookbookId));
        householdServiceObservers.forEach(observer -> observer.onHouseholdCreation(household));
        return household;
	}

	public void deleteHousehold(Long householdId) {
        Household household = householdRepository.determineHousehold(householdId);
        householdRepository.deleteHousehold(householdId);
        householdServiceObservers.forEach(observer -> observer.onHouseholdDeletion(household));
    }
}
