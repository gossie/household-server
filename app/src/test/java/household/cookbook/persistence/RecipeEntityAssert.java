package household.cookbook.persistence;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class RecipeEntityAssert extends AbstractAssert<RecipeEntityAssert, RecipeEntity> {

	private RecipeEntityAssert(RecipeEntity actual) {
		super(actual, RecipeEntityAssert.class);
	}

	public static RecipeEntityAssert assertThat(RecipeEntity actual) {
		return new RecipeEntityAssert(actual);
	}

	public RecipeEntityAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public RecipeEntityAssert hasDescription(String description) {
		Assertions.assertThat(actual.getDescription()).isEqualTo(description);
		return this;
	}

	public RecipeEntityAssert hasSize(int size) {
		Assertions.assertThat(actual.getIngredients()).hasSize(size);
		return this;

	}

	public RecipeEntityAssert hasNoIngredients() {
		Assertions.assertThat(actual.getIngredients()).isEmpty();
		return this;
	}

	public RecipeEntityAssert ingredient(int index, Consumer<IngredientEntityAssert> consumer) {
		consumer.accept(IngredientEntityAssert.assertThat(actual.getIngredients().get(index)));
		return this;
	}
}
