package household.foodplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class MealDTOMapper {

	Meal map(MealDTO meal) {
		return new Meal(null, meal.getName(), null);
	}
	
	MealDTO map(Meal meal) {
		return new MealDTO(meal.getId(), meal.getName(), meal.getRecipeId().orElse(null));
	}
}
