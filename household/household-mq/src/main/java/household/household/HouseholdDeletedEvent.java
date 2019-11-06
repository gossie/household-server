package household.household;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
class HouseholdDeletedEvent {

    private final Long householdId;
    private final Long shoppingListId;
    private final Long cleaningPlanId;
    private final Long foodPlanId;
    private final Long cookbookId;

}
