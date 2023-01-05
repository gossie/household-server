package household.cleaningplan.messaging;

import com.google.common.eventbus.EventBus;

import household.cleaningplan.domain.CleaningPlanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CleaningPlanMQContext {

    @Bean(initMethod = "init")
    public CleaningPlanEventHandler cleaningPlanEventHandler(EventBus eventBus, CleaningPlanService cleaningPlanService) {
        return new CleaningPlanEventHandler(eventBus, cleaningPlanService);
    }

}
