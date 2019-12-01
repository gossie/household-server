package household.shoppinglist;

import household.HouseholdMessageChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
@EnableResourceServer
public class ShoppingListApplication {

    public static void main(String[] args) {
		SpringApplication.run(ShoppingListApplication.class, args);
	}

	@Bean
	public ShoppingListService shoppingListService(ShoppingListRepository shoppingListRepository) {
		return new ShoppingListService(shoppingListRepository);
	}

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
