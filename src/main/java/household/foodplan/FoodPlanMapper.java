package household.foodplan;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FoodPlanMapper {

	private final MealMapper mealMapper;
	
	public FoodPlan map(FoodPlanDTO foodPlan) {
		Map<String, Meal> meals = foodPlan.getMeals().entrySet().stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> mealMapper.map(entry.getValue())));
		return new FoodPlan(meals);
	}
	
	public FoodPlanDTO map(FoodPlan foodPlan) {
		Map<String, MealDTO> meals = foodPlan.getMeals().entrySet().stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> mealMapper.map(entry.getValue())));
		return new FoodPlanDTO(foodPlan.getId(), meals);
	}
}
