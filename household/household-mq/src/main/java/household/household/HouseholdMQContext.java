package household.household;

import household.HouseholdMessageChannels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HouseholdMQContext {

    @Bean(initMethod = "init")
    public HouseholdServiceEventHandler householdEventHandler(HouseholdService householdService, HouseholdMessageChannels householdMessageChannels) {
        return new HouseholdServiceEventHandler(householdService, householdMessageChannels.creationConsumer(), householdMessageChannels.deletionConsumer());
    }

}
