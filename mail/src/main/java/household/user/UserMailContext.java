package household.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMailContext {

    @Bean
    public ConfirmationMailService confirmationMailService(UserService userService) {
        return new ConfirmationMailService(userService);
    }
}