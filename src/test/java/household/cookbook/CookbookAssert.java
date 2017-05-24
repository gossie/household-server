package household.cookbook;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CookbookAssert extends AbstractAssert<CookbookAssert, Cookbook> {

	private CookbookAssert(Cookbook actual) {
		super(actual, CookbookAssert.class);
	}
	
	public static CookbookAssert assertThat(Cookbook actual) {
		return new CookbookAssert(actual);
	}
	
	public CookbookAssert hasSize(int size) {
		Assertions.assertThat(actual.getRecipes()).hasSize(size);
		return this;
	}

	public CookbookAssert recipe(int index, Consumer<RecipeAssert> consumer) {
		consumer.accept(RecipeAssert.assertThat(actual.getRecipes().get(index)));
		return this;
	}
}
