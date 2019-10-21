package household.foodplan;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class MealMapper {

    MealEntity map(Meal meal) {
        String recipeReference = meal.getRecipe()
                .map(recipe -> "" + recipe.getCookbookId() + "_" + recipe.getId())
                .orElse(null);

        return new MealEntity(meal.getId(), meal.getName(), recipeReference);
    }
    
    Meal map(MealEntity meal) {
        Recipe recipe = Optional.ofNullable(meal.getRecipeReference())
                .map(reference -> reference.split("_"))
                .map(array -> new Recipe(Long.valueOf(array[1]), Long.valueOf(array[0])))
                .orElse(null);
                
        return new Meal(meal.getId(), meal.getName(), recipe);
    }
}
