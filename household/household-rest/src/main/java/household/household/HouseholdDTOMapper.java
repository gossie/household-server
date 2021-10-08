package household.household;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class HouseholdDTOMapper {

	HouseholdDTO map(Household household) {
		return new HouseholdDTO(household.id(), household.shoppingListId(), household.cleaningPlanId(), household.foodPlanId(), household.cookbookId());
	}
}
