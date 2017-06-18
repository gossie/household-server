package household.cookbook;

import java.util.List;

import household.AbstractDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class RecipeDTO extends AbstractDTO {

	private final Long databaseId;
	private final String name;
	private final List<IngredientDTO> ingredients;
}
