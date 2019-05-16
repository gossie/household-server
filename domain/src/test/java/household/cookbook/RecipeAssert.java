package household.cookbook;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Strings;

public class RecipeAssert extends AbstractAssert<RecipeAssert, Recipe> {

	private RecipeAssert(Recipe actual) {
		super(actual, RecipeAssert.class);
	}
	
	public static RecipeAssert assertThat(Recipe actual) {
		return new RecipeAssert(actual);
	}
	
	public RecipeAssert hasId(Long id) {
		Assertions.assertThat(actual.getId()).isEqualTo(id);
		return this;
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

    public RecipeAssert hasUrl(String url) {
	    Assertions.assertThat(actual.getUrl()).isEqualTo(url);
	    return this;
    }

    public RecipeAssert hasNoUrl() {
	    Assertions.assertThat(actual.getUrl()).isEmpty();
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
