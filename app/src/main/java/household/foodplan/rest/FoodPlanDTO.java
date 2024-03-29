package household.foodplan.rest;

import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class FoodPlanDTO extends AbstractDTO {

	private String databaseId;
	private Map<String, MealDTO> meals;
}
