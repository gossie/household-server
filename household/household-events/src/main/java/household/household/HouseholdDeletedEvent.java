package household.household;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class HouseholdDeletedEvent {

	private final Long householdId;
	private final Long shoppingListId;
	private final Long cleaningPlanId;
	private final Long foodPlanId;
	private final Long cookbookId;

}
