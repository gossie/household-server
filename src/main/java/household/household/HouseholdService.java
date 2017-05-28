package household.household;

import org.springframework.stereotype.Component;

import household.cleaningplan.CleaningPlan;
import household.cookbook.Cookbook;
import household.foodplan.FoodPlan;
import household.shoppinglist.ShoppingList;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HouseholdService {

	private final HouseholdRepository householdRepository;
	
	public Household getHousehold(Long id) {
		return householdRepository.findOne(id);
	}

	public Household createHousehold() {
		ShoppingList shoppingList = null;
		CleaningPlan cleaningPlan = null;
		FoodPlan foodPlan = null;
		Cookbook cookbook = null;
		return householdRepository.save(new Household(shoppingList, cleaningPlan, foodPlan, cookbook));
	}
}
