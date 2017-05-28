package household.cookbook;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class RecipeAssert extends AbstractAssert<RecipeAssert, RecipeEntity> {

	private RecipeAssert(RecipeEntity actual) {
		super(actual, RecipeAssert.class);
	}
	
	public static RecipeAssert assertThat(RecipeEntity actual) {
		return new RecipeAssert(actual);
	}
	
	public RecipeAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public RecipeAssert hasDescription(String description) {
		Assertions.assertThat(actual.getDescription()).isEqualTo(description);
		return this;
	}

	public RecipeAssert hasSize(int size) {
		Assertions.assertThat(actual.getIngredients()).hasSize(size);
		return this;
		
	}

	public RecipeAssert hasNoIngredients() {
		Assertions.assertThat(actual.getIngredients()).isEmpty();
		return this;
	}
	
	public RecipeAssert ingredient(int index, Consumer<IngredientAssert> consumer) {
		consumer.accept(IngredientAssert.assertThat(actual.getIngredients().get(index)));
		return this;
	}
}
