package household.foodplan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodPlanService {

    private final FoodPlanRepository foodPlanRepository;

    public FoodPlan getFoodPlan(String foodPlanId) {
        return foodPlanRepository.determineFoodPlan(foodPlanId);
    }

    public FoodPlan clear(String foodPlanId) {
        FoodPlan foodPlan = foodPlanRepository.determineFoodPlan(foodPlanId);
        foodPlan.clear();
        return foodPlanRepository.saveFoodPlan(foodPlan);
    }

    public FoodPlan updateMeal(String foodPlanId, String mealId, Recipe recipe, Meal meal) {
        FoodPlan saved = foodPlanRepository.determineFoodPlan(foodPlanId);
        saved.updateMeal(mealId, recipe, meal);
        return foodPlanRepository.saveFoodPlan(saved);
    }

    public FoodPlan createFoodPlan() {
        return foodPlanRepository.createFoodPlan();
    }

    public void deleteFoodPlan(String foodPlanId) {
        foodPlanRepository.deleteFoodPlan(foodPlanId);
    }

}
