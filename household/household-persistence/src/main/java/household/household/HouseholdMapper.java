package household.household;

class HouseholdMapper {

	Household map(HouseholdEntity household) {
		return new Household(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId());
	}
	
	HouseholdEntity map(Household household) {
		return new HouseholdEntity(household.id(), household.shoppingListId(), household.cleaningPlanId(), household.foodPlanId(), household.cookbookId());
	}
}
