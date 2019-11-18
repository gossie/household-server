package household.cleaningplan;

import household.HouseholdMessageChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@EnableBinding(HouseholdMessageChannels.class)
class CleaningPlanMQContext {

    @Bean
    public CleaningPlanEventHandler cleaningPlanEventHandler(CleaningPlanService cleaningPlanService) {
        return new CleaningPlanEventHandler(cleaningPlanService);
    }

}
