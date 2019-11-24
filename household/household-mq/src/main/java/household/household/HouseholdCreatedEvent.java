package household.household;

import lombok.Value;

@Value
public class HouseholdCreatedEvent {

    private final Long householdId;
    private final Long shoppingListId;
    private final Long cleaningPlanId;
    private final Long foodPlanId;
    private final Long cookbookId;

}
