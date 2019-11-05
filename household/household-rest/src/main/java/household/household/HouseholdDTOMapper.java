package household.household;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class HouseholdDTOMapper {

	HouseholdDTO map(Household household) {
		return new HouseholdDTO(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId());
	}
}
