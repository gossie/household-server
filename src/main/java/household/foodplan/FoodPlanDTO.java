package household.foodplan;

import java.util.Map;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FoodPlanDTO extends AbstractDTO {

	private final Long databaseId;
	private final Map<String, MealDTO> meals;
}
