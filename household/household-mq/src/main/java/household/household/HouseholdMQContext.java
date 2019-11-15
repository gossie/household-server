package household.household;

import household.HouseholdMessageChannels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HouseholdMQContext {

    @Bean(initMethod = "init")
    public HouseholdEventHandler householdEventHandler(HouseholdService householdService, HouseholdMessageChannels householdMessageChannels) {
        return new HouseholdEventHandler(householdService, householdMessageChannels.creationConsumer(), householdMessageChannels.deletionConsumer());
    }

}
