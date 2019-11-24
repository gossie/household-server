package household.cookbook;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class RecipeDTO extends AbstractDTO {

	private Long databaseId;
	private String name;
	private List<IngredientDTO> ingredients;
	private String url;
}
