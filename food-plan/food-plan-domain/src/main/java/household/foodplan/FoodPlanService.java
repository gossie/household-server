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

    public FoodPlan updateMeal(Long foodPlanId, Long mealId, Recipe recipe, Meal meal) {
        FoodPlan saved = foodPlanRepository.determineFoodPlan(foodPlanId);
        saved.updateMeal(mealId, recipe, meal);
        return foodPlanRepository.saveFoodPlan(saved);
    }

    public FoodPlan createFoodPlan() {
        return foodPlanRepository.createFoodPlan();
    }

    public void deleteFoodPlan(Long foodPlanId) {
        foodPlanRepository.deleteFoodPlan(foodPlanId);
    }

}
