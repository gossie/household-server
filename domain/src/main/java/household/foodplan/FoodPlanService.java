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

	public FoodPlan update(Long id, FoodPlan foodPlan) {
		FoodPlan saved = foodPlanRepository.determineFoodPlan(id);
		saved.update(foodPlan);
		return foodPlanRepository.saveFoodPlan(saved);
	}

	public FoodPlan createFoodPlan() {
		return foodPlanRepository.createFoodPlan();
	}

    private void deleteCookbook(Long foodPlanId) {
        foodPlanRepository.deleteFoodPlan(foodPlanId);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        deleteCookbook(event.getHousehold().getFoodPlanId());
    }
}
