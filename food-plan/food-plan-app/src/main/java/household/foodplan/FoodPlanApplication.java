package household.foodplan;

import household.HouseholdMessageChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
public class FoodPlanApplication {

    public static void main(String[] args) {
		SpringApplication.run(FoodPlanApplication.class, args);
	}

	@Bean
	public FoodPlanService shoppingListService(FoodPlanRepository shoppingListRepository) {
		return new FoodPlanService(shoppingListRepository);
	}

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
