package household.foodplan;

import household.HouseholdMessageChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@EnableBinding(HouseholdMessageChannels.class)
class FoodPlanMQContext {

    @Bean
    public FoodPlanEventHandler foodPlanEventHandler(FoodPlanService foodPlanService) {
        return new FoodPlanEventHandler(foodPlanService);
    }

}
