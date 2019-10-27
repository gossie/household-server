package household.cookbook;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CookbookDTO extends AbstractDTO {

	private final Long databaseId;
	private final List<RecipeDTO> recipes;

	@Override
	public Long getDatabaseId() {
		return databaseId;
	}

}
