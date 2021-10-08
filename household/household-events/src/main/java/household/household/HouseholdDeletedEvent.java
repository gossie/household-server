package household.household;

public record HouseholdDeletedEvent(
		Long householdId,
		Long shoppingListId,
		Long cleaningPlanId,
		Long foodPlanId,
		Long cookbookId
) {}
