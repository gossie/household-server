package household.foodplan;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodPlanService {

    private final EventBus eventBus;
    private final FoodPlanRepository foodPlanRepository;

    public void init() {
        eventBus.register(this);
    }

    public FoodPlan getFoodPlan(Long foodPlanId) {
        return foodPlanRepository.determineFoodPlan(foodPlanId);
    }

    public FoodPlan clear(Long foodPlanId) {
        FoodPlan foodPlan = foodPlanRepository.determineFoodPlan(foodPlanId);
        foodPlan.clear();
        return foodPlanRepository.saveFoodPlan(foodPlan);
    }

    public FoodPlan saveMeal(Long foodPlanId, Long mealId, Meal meal) {
        FoodPlan saved = foodPlanRepository.determineFoodPlan(foodPlanId);
        saved.updateMeal(mealId, meal);
        return foodPlanRepository.saveFoodPlan(saved);
    }

    public FoodPlan createFoodPlan() {
        return foodPlanRepository.createFoodPlan();
    }

    private void deleteFoodplan(Long foodPlanId) {
        foodPlanRepository.deleteFoodPlan(foodPlanId);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        deleteFoodplan(event.getHousehold().getFoodPlanId());
    }
}
