package household.household;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class HouseholdDeletedEvent {

	private final String householdId;
	private final String shoppingListId;
	private final String cleaningPlanId;
	private final String foodPlanId;
	private final String cookbookId;

}
