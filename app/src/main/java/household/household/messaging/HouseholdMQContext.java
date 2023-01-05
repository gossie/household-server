package household.household.messaging;

import com.google.common.eventbus.EventBus;

import household.household.domain.HouseholdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HouseholdMQContext {

    @Bean(initMethod = "init")
    public HouseholdEventHandler householdEventHandler(EventBus eventBus, HouseholdService householdService) {
        return new HouseholdEventHandler(eventBus, householdService);
    }

    @Bean
    public HouseholdEventEmitter householdEventEmiter(EventBus eventBus) {
    	return new HouseholdEventEmitter(eventBus);
    }

}
