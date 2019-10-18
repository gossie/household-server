package household.foodplan;

import household.AbstractDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class MealDTO extends AbstractDTO {

	private Long databaseId;
	private String name;
	private Long recipeId;
}
