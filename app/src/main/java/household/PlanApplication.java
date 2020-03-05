package household;

import com.google.common.eventbus.EventBus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import household.cleaningplan.CleaningPlanRepository;
import household.cleaningplan.CleaningPlanService;
import household.cookbook.CookbookRepository;
import household.cookbook.CookbookService;
import household.foodplan.FoodPlanRepository;
import household.foodplan.FoodPlanService;
import household.household.HouseholdRepository;
import household.household.HouseholdService;
import household.shoppinglist.ShoppingListRepository;
import household.shoppinglist.ShoppingListService;
import household.user.UserRepository;
import household.user.UserService;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class PlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanApplication.class, args);
	}

	@Bean
    public EventBus eventBus() {
	    return new EventBus();
    }

}
