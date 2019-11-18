package household.foodplan;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
class FoodPlanEventHandler {

    private final FoodPlanService foodPlanService;

    @StreamListener(HouseholdMessageChannels.DELETION_PRODUCER)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        foodPlanService.deleteFoodPlan(event.getFoodPlanId());
    }

}
