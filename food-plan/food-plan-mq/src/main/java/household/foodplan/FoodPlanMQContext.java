package household.foodplan;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FoodPlanMQContext {

    @Bean(initMethod = "init")
    public FoodPlanEventHandler foodPlanEventHandler(EventBus eventBus, FoodPlanService foodPlanService) {
        return new FoodPlanEventHandler(eventBus, foodPlanService);
    }

}
