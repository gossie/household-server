package household.household;

import lombok.Value;

@Value
class HouseholdDeletedEvent {

    private final Long householdId;
    private final Long shoppingListId;
    private final Long cleaningPlanId;
    private final Long foodPlanId;
    private final Long cookbookId;

}
