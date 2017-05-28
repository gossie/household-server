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

	public Household createHousehold() {
		ShoppingList shoppingList = null;
		CleaningPlan cleaningPlan = null;
		FoodPlan foodPlan = null;
		Cookbook cookbook = null;
		return householdRepository.saveHousehold(new Household(null, shoppingList, cleaningPlan, foodPlan, cookbook));
	}
}
