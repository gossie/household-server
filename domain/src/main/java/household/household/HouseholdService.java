package household.household;

import household.cleaningplan.CleaningPlan;
import household.cookbook.Cookbook;
import household.foodplan.FoodPlan;
import household.shoppinglist.ShoppingList;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	
	public Household getHousehold(Long householdId) {
		return householdRepository.determineHousehold(householdId);
	}

	public Household createHousehold(ShoppingList shoppingList, CleaningPlan cleaningPlan, FoodPlan foodPlan, Cookbook cookbook) {
		return householdRepository.saveHousehold(new Household(null, shoppingList.getId(), cleaningPlan.getId(), foodPlan.getId(), cookbook.getId()));
	}
}
