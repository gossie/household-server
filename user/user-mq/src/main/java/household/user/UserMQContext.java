package household.user;

import household.HouseholdMessageChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@EnableBinding(HouseholdMessageChannels.class)
class UserMQContext {

    @Bean(initMethod = "init")
    public UserEventHandler userEventHandler(UserService UserService) {
        return new UserEventHandler(UserService);
    }

}
