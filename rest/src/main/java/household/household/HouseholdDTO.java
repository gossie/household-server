package household.household;

import com.fasterxml.jackson.annotation.JsonIgnore;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HouseholdDTO extends AbstractDTO {

	private final Long databaseId;
	private final Long shoppingListId;
	private final Long cleaningPlanId;
	private final Long foodPlanId;
	private final Long cookbookId;
	
	@JsonIgnore
	public Long getShoppingListId() {
		return shoppingListId;
	}
	
	@JsonIgnore
	public Long getCleaningPlanId() {
		return cleaningPlanId;
	}
	
	@JsonIgnore
	public Long getFoodPlanId() {
		return foodPlanId;
	}
	
	@JsonIgnore
	public Long getCookbookId() {
		return cookbookId;
	}
	
}
