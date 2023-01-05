package household.foodplan.rest;

import household.foodplan.domain.Meal;
import household.foodplan.domain.Recipe;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class MealDTOMapper {

	Meal map(MealDTO meal) {
		return new Meal(null, meal.getName(), null);
	}

	MealDTO map(Meal meal) {
		String cookbookId = meal.getRecipe().map(Recipe::getCookbookId).orElse(null);
		String recipeId = meal.getRecipe().map(Recipe::getId).orElse(null);

		return new MealDTO(meal.getId(), meal.getName(), cookbookId, recipeId);
	}
}
