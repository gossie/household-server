package household.household.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HouseholdDTO extends AbstractDTO {

	private final String databaseId;
	private final String shoppingListId;
	private final String cleaningPlanId;
	private final String foodPlanId;
	private final String cookbookId;
    private List<ParticipantDTO> participants = new ArrayList<>();

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
