package household.cookbook;

import java.util.List;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RecipeDTO extends AbstractDTO {

	private final Long databaseId;
	private final String name;
	private final List<IngredientDTO> ingredients;
}
