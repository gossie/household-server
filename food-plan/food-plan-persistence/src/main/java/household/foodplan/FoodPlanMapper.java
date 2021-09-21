package household.foodplan;

import java.util.Map;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class FoodPlanMapper {

	private final MealMapper mealMapper;

	public FoodPlanEntity map(FoodPlan foodPlan) {
		Map<String, MealEntity> meals = foodPlan.getMeals().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> mealMapper.map(entry.getValue())));
		return new FoodPlanEntity(foodPlan.getId(), meals);
	}

	public FoodPlan map(FoodPlanEntity foodPlan) {
		Map<String, Meal> meals = foodPlan.getMeals().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> mealMapper.map(entry.getValue())));
		return new FoodPlan(foodPlan.getId(), meals);
	}
}
