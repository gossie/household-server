package household.cleaningplan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CleaningPlanMQContext {

    @Bean
    public CleaningPlanEventHandler cleaningPlanEventHandler(CleaningPlanService cleaningPlanService) {
        return new CleaningPlanEventHandler(cleaningPlanService);
    }

}
