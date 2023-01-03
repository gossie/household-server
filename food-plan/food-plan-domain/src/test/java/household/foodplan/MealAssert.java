package household.foodplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class MealAssert extends AbstractAssert<MealAssert, Meal> {

    private MealAssert(Meal actual) {
        super(actual, MealAssert.class);
    }

    public static MealAssert assertThat(Meal actual) {
        return new MealAssert(actual);
    }

    public MealAssert hasId(String id) {
    Assertions.assertThat(actual.getId()).isEqualTo(id);
        return this;
    }
    
    public MealAssert hasName(String name) {
        Assertions.assertThat(actual.getName()).isEqualTo(name);
        return this;
    }

    public MealAssert hasNoRecipe() {
        Assertions.assertThat(actual.getRecipe()).isEmpty();
        return this;
    }

    public MealAssert hasRecipe(Recipe recipe) {
        Assertions.assertThat(actual.getRecipe()).contains(recipe);
        return this;
    }
}
