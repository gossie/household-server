package household.cookbook.rest;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CookbookDTO extends AbstractDTO {

	private final String databaseId;
	private final List<RecipeDTO> recipes;

	@Override
	public String getDatabaseId() {
		return databaseId;
	}

}
