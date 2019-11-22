package household;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import household.household.HouseholdRepository;
import household.household.HouseholdService;
import household.user.UserRepository;
import household.user.UserService;
import org.springframework.http.HttpMethod;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
public class GatewayApplication {

    public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public HouseholdService householdService(HouseholdRepository householdRepository) {
		return new HouseholdService(householdRepository);
	}

	@Bean
	public UserService userService(UserRepository userRepository) {
		return new UserService(userRepository);
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
                                     @Value("${cookbook.url}") String cookbookUrl) {

        System.out.println("gateway routes are created");
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
            .build();
    }

}
