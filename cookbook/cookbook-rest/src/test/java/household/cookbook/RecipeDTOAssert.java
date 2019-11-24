package household.cookbook;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class RecipeDTOAssert extends AbstractAssert<RecipeDTOAssert, RecipeDTO> {

	private RecipeDTOAssert(RecipeDTO actual) {
		super(actual, RecipeDTOAssert.class);
	}
	
	public static RecipeDTOAssert assertThat(RecipeDTO actual) {
		return new RecipeDTOAssert(actual);
	}
	
	public RecipeDTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public RecipeDTOAssert hasSize(int size) {
		Assertions.assertThat(actual.getIngredients()).hasSize(size);
		return this;
	}

	public RecipeDTOAssert ingredient(int index, Consumer<IngredientDTOAssert> consumer) {
        consumer.accept(IngredientDTOAssert.assertThat(actual.getIngredients().get(index)));
		return this;
	}
}
