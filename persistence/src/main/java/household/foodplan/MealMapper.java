package household.foodplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MealMapper {

	public MealEntity map(Meal meal) {
		return new MealEntity(meal.getId(), meal.getName());
	}
	
	public Meal map(MealEntity meal) {
		return new Meal(meal.getId(), meal.getName());
	}
}
