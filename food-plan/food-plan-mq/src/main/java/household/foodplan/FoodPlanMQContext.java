package household.foodplan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FoodPlanMQContext {

    public FoodPlanEventHandler foodPlanEventHandler(FoodPlanService foodPlanService) {
        return new FoodPlanEventHandler(foodPlanService);
    }

}
