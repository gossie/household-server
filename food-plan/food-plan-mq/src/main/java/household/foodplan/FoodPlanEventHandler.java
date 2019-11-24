package household.foodplan;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
class FoodPlanEventHandler {

    private final FoodPlanService foodPlanService;

    @StreamListener(HouseholdMessageChannels.DELETION_INPUT)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        foodPlanService.deleteFoodPlan(event.getFoodPlanId());
    }

    @StreamListener(HouseholdMessageChannels.CREATION_INPUT)
    public void onHouseholdCreation(HouseholdCreatedEvent event) {
        System.out.println("food-plan-service: noticed that household with id [" + event.getHouseholdId() + "] was created. But I don't care!");
    }

}
