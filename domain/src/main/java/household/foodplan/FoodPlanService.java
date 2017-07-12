package household.foodplan;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodPlanService {

	private final FoodPlanRepository foodPlanRepository;

	public FoodPlan getFoodPlan(Long foodPlanId) {
		return foodPlanRepository.determineFoodPlan(foodPlanId);
	}
	
	public FoodPlan clear(Long foodPlanId) {
		FoodPlan foodPlan = foodPlanRepository.determineFoodPlan(foodPlanId);
		foodPlan.clear();
		return foodPlanRepository.saveFoodPlan(foodPlan);
	}

	public FoodPlan update(Long id, FoodPlan foodPlan) {
		FoodPlan saved = foodPlanRepository.determineFoodPlan(id);
		saved.update(foodPlan);
		return foodPlanRepository.saveFoodPlan(saved);
	}

	public FoodPlan createFoodPlan() {
		return foodPlanRepository.createFoodPlan();		
	}
}
