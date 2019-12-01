package household.household;

import household.HouseholdMessageChannels;
import household.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
@EnableResourceServer
public class HouseholdApplication {

    public static void main(String[] args) {
		SpringApplication.run(HouseholdApplication.class, args);
	}

    @Bean
    public HouseholdService householdService(HouseholdRepository householdRepository) {
        return new HouseholdService(householdRepository);
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
