package household.cleaningplan;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CleaningPlanMQContext {

    @Bean(initMethod = "init")
    public CleaningPlanEventHandler cleaningPlanEventHandler(EventBus eventBus, CleaningPlanService cleaningPlanService) {
        return new CleaningPlanEventHandler(eventBus, cleaningPlanService);
    }

}
