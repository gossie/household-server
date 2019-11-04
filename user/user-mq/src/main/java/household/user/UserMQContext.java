package household.user;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserMQContext {

    @Bean(initMethod = "init")
    public UserEventHandler userEventHandler(EventBus eventBus, UserService UserService) {
        return new UserEventHandler(eventBus, UserService);
    }

}
