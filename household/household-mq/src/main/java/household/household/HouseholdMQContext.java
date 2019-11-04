package household.household;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HouseholdMQContext {

    @Bean(initMethod = "init")
    public HouseholdEventHandler householdEventHandler(EventBus eventBus, HouseholdService householdService) {
        return new HouseholdEventHandler(eventBus, householdService);
    }

}
