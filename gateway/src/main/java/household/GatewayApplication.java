package household;

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
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        System.out.println("gateway routes are created");
        return builder.routes()
            .route(spec -> spec
                .path("/api/shoppingLists/**")
                .uri("http://shopping-list:8081"))
            .route(spec -> spec
                .path("/api/cleaningPlans/**")
                .uri("http://cleaning-plan:8082"))
            .route(spec -> spec
                .path("/api/foodPlans/**")
                .uri("http://food-plan:8083"))
            .route(spec -> spec
                .path("/api/cookbooks/**")
                .uri("http://cookbook:8084"))
            .build();
    }

}
