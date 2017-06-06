package household.foodplan;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MealDTO extends AbstractDTO {

	private final Long databaseId;
	private final String name;
}
