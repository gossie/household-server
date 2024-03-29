package household.cookbook.rest;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class RecipeDTO extends AbstractDTO {

	private String databaseId;
	private String name;
	private List<IngredientDTO> ingredients;
	private String url;
}
