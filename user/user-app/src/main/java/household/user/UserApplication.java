package household.user;

import household.HouseholdMessageChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
public class UserApplication {

    public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public UserService shoppingListService(UserRepository userRepository) {
		return new UserService(userRepository);
	}

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
