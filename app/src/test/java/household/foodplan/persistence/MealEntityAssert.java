package household.foodplan.persistence;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

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

    public MealEntityAssert hasRecipeReference(String recipeReference) {
        Assertions.assertThat(actual.getRecipeReference()).isEqualTo(recipeReference);
        return this;
    }
}
