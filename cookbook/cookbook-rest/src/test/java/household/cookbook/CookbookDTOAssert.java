package household.cookbook;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CookbookDTOAssert extends AbstractAssert<CookbookDTOAssert, CookbookDTO> {

	private CookbookDTOAssert(CookbookDTO actual) {
		super(actual, CookbookDTOAssert.class);
	}

	public static CookbookDTOAssert assertThat(CookbookDTO actual) {
		return new CookbookDTOAssert(actual);
	}

	public CookbookDTOAssert hasDatabaseId(String databaseId) {
		Assertions.assertThat(actual.getDatabaseId()).isEqualTo(databaseId);
		return this;
	}
	
	public CookbookDTOAssert hasSize(int size) {
		Assertions.assertThat(actual.getRecipes()).hasSize(size);
		return this;
	}
}
