package household.cookbook;

import household.HouseholdMessageChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
@EnableResourceServer
public class CookbookApplication {

    public static void main(String[] args) {
		SpringApplication.run(CookbookApplication.class, args);
	}

	@Bean
	public CookbookService shoppingListService(CookbookRepository shoppingListRepository) {
		return new CookbookService(shoppingListRepository);
	}

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
