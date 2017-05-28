package household.foodplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import household.cookbook.RecipeEntity;

public class MealEntityAssert extends AbstractAssert<MealEntityAssert, MealEntity> {

	private MealEntityAssert(MealEntity actual) {
		super(actual, MealEntityAssert.class);
	}

	public static MealEntityAssert assertThat(MealEntity actual) {
		return new MealEntityAssert(actual);
	}
	
	public MealEntityAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public MealEntityAssert hasNoRecipe() {
		Assertions.assertThat(actual.getRecipe()).isEmpty();
		return this;
	}

	public MealEntityAssert hasRecipe(RecipeEntity recipe) {
		Assertions.assertThat(actual.getRecipe()).contains(recipe);
		return this;
	}
}
