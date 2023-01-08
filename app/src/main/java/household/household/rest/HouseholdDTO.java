package household.household.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HouseholdDTO extends AbstractDTO {

	private String databaseId;
	private String shoppingListId;
	private String cleaningPlanId;
	private String foodPlanId;
	private String cookbookId;
    private List<ParticipantDTO> participants = new ArrayList<>();

	public HouseholdDTO(String databaseId, String shoppingListId, String cleaningPlanId, String foodPlanId, String cookbookId) {
		this.databaseId = databaseId;
		this.shoppingListId = shoppingListId;
		this.cleaningPlanId = cleaningPlanId;
		this.foodPlanId = foodPlanId;
		this.cookbookId = cookbookId;
	}

    public void addParticipant(ParticipantDTO participant) {
        participants.add(participant);
    }

	@JsonIgnore
	public String getShoppingListId() {
		return shoppingListId;
	}

	@JsonIgnore
	public String getCleaningPlanId() {
		return cleaningPlanId;
	}

	@JsonIgnore
	public String getFoodPlanId() {
		return foodPlanId;
	}

	@JsonIgnore
	public String getCookbookId() {
		return cookbookId;
	}

}
