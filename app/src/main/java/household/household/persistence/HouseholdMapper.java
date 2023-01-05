package household.household.persistence;

import household.household.domain.Household;

class HouseholdMapper {

	Household map(HouseholdEntity household) {
		return new Household(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId());
	}

	HouseholdEntity map(Household household) {
		return new HouseholdEntity(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId());
	}
}
