package household.foodplan.rest;

import java.util.Map;
import java.util.stream.Collectors;

import household.foodplan.domain.FoodPlan;
import household.foodplan.domain.Meal;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class FoodPlanDTOMapper {

	private final MealDTOMapper mealMapper;

	FoodPlan map(FoodPlanDTO foodPlan) {
		Map<String, Meal> meals = foodPlan.getMeals().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> mealMapper.map(entry.getValue())));
		return new FoodPlan(foodPlan.getDatabaseId(), meals);
	}

	FoodPlanDTO map(FoodPlan foodPlan) {
		Map<String, MealDTO> meals = foodPlan.getMeals().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> mealMapper.map(entry.getValue())));
		return new FoodPlanDTO(foodPlan.getId(), meals);
	}
}
