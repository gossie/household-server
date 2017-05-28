package household.foodplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import household.cookbook.RecipeEntity;

public class MealAssert extends AbstractAssert<MealAssert, MealEntity> {

	private MealAssert(MealEntity actual) {
		super(actual, MealAssert.class);
	}

	public static MealAssert assertThat(MealEntity actual) {
		return new MealAssert(actual);
	}
	
	public MealAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public MealAssert hasNoRecipe() {
		Assertions.assertThat(actual.getRecipe()).isEmpty();
		return this;
	}

	public MealAssert hasRecipe(RecipeEntity recipe) {
		Assertions.assertThat(actual.getRecipe()).contains(recipe);
		return this;
	}
}
