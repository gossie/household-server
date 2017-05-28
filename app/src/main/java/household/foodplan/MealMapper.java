package household.foodplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MealMapper {

	public MealEntity map(MealDTO meal) {
		return new MealEntity(meal.getName());
	}
	
	public MealDTO map(MealEntity meal) {
		return new MealDTO(meal.getId(), meal.getName());
	}
}
