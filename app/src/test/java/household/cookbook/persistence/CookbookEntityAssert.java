package household.cookbook.persistence;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CookbookEntityAssert extends AbstractAssert<CookbookEntityAssert, CookbookEntity> {

	private CookbookEntityAssert(CookbookEntity actual) {
		super(actual, CookbookEntityAssert.class);
	}

	public static CookbookEntityAssert assertThat(CookbookEntity actual) {
		return new CookbookEntityAssert(actual);
	}

	public CookbookEntityAssert hasSize(int size) {
		Assertions.assertThat(actual.getRecipes()).hasSize(size);
		return this;
	}

	public CookbookEntityAssert recipe(int index, Consumer<RecipeEntityAssert> consumer) {
		consumer.accept(RecipeEntityAssert.assertThat(actual.getRecipes().get(index)));
		return this;
	}
}
