package household.foodplan.persistence;

import java.util.Optional;

import household.foodplan.domain.Meal;
import household.foodplan.domain.Recipe;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class MealMapper {

    MealEntity map(Meal meal) {
        String recipeReference = meal.getRecipe()
                .map(recipe -> recipe.getCookbookId() + "_" + recipe.getId())
                .orElse(null);

        return new MealEntity(meal.getId(), meal.getName(), recipeReference);
    }

    Meal map(MealEntity meal) {
        Recipe recipe = Optional.ofNullable(meal.getRecipeReference())
                .map(reference -> reference.split("_"))
                .map(array -> new Recipe(array[1], array[0]))
                .orElse(null);

        return new Meal(meal.getId(), meal.getName(), recipe);
    }
}
