package household.foodplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FoodPlanService {

	private final FoodPlanRepository foodPlanRepository;

	public FoodPlan getFoodPlan(Long id) {
		return foodPlanRepository.findOne(1L);
	}
	
	public FoodPlan clear(Long foodPlanId) {
		FoodPlan foodPlan = foodPlanRepository.findOne(foodPlanId);
		foodPlan.clear();
		return foodPlanRepository.save(foodPlan);
	}

	public FoodPlan update(Long id, FoodPlan foodPlan) {
		FoodPlan saved = foodPlanRepository.findOne(id);
		saved.update(foodPlan);
		return foodPlanRepository.save(saved);
	}
}
