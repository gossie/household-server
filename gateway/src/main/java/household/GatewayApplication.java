package household;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
@EnableOAuth2Sso
public class GatewayApplication {

    public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
    public LogAspect logAspect() {
	    return new LogAspect();
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${shopping-list.url}") String shoppingListUrl,
                                     @Value("${cleaning-plan.url}") String cleaningPlanUrl,
                                     @Value("${food-plan.url}") String foodPlanUrl,
                                     @Value("${cookbook.url}") String cookbookUrl,
                                     @Value("${household.url}") String householdUrl) {

        return builder.routes()
            .route(spec -> spec
                .path("/api/shoppingLists/**")
                .uri(shoppingListUrl))
            .route(spec -> spec
                .path("/api/cleaningPlans/**")
                .uri(cleaningPlanUrl))
            .route(spec -> spec
                .path("/api/foodPlans/**")
                .uri(foodPlanUrl))
            .route(spec -> spec
                .path("/api/cookbooks/**")
                .uri(cookbookUrl))
            .route(spec -> spec
                .path("/api/households/**")
                .uri(householdUrl))
            .build();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationProvider() {
        return new OAuth2PasswordAuthenticationManager(webClient());
    }
}
