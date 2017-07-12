package household.foodplan;

import java.util.Map;

import household.AbstractDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class FoodPlanDTO extends AbstractDTO {

	private Long databaseId;
	private Map<String, MealDTO> meals;
}
