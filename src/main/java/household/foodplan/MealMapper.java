package household.foodplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MealMapper {

	public Meal map(MealDTO meal) {
		return new Meal(meal.getName());
	}
	
	public MealDTO map(Meal meal) {
		return new MealDTO(meal.getId(), meal.getName());
	}
}
