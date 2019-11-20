package household.cleaningplan;

import household.HouseholdMessageChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
public class CleaningPlanApplication {

    public static void main(String[] args) {
		SpringApplication.run(CleaningPlanApplication.class, args);
	}

	@Bean
	public CleaningPlanService shoppingListService(CleaningPlanRepository shoppingListRepository) {
		return new CleaningPlanService(shoppingListRepository);
	}

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
