package household.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserMQContext {

    public UserEventHandler userEventHandler(UserService UserService) {
        return new UserEventHandler(UserService);
    }

}
