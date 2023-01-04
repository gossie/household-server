package household.foodplan.messaging;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.foodplan.domain.FoodPlanService;
import household.household.events.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FoodPlanEventHandler {

    private final EventBus eventBus;
    private final FoodPlanService foodPlanService;

    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        foodPlanService.deleteFoodPlan(event.getFoodPlanId());
    }

}
