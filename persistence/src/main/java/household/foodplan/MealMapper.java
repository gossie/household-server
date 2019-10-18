package household.foodplan;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class MealMapper {

	MealEntity map(Meal meal) {
		return new MealEntity(meal.getId(), meal.getName(), meal.getRecipeId().orElse(null));
	}
	
	Meal map(MealEntity meal) {
		return new Meal(meal.getId(), meal.getName(), meal.getRecipeId());
	}
}
