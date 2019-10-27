package household;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableEntityLinks;

import com.google.common.eventbus.EventBus;
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

@SpringBootApplication
@EnableEntityLinks
public class PlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanApplication.class, args);
	}

	@Bean
    public EventBus eventBus() {
	    return new EventBus();
    }

	@Bean(initMethod = "init")
	public HouseholdService householdService(HouseholdRepository householdRepository) {
		return new HouseholdService(eventBus(), householdRepository);
	}

	@Bean(initMethod = "init")
	public CleaningPlanService cleaningPlanService(CleaningPlanRepository cleaningPlanRepository) {
		return new CleaningPlanService(eventBus(), cleaningPlanRepository);
	}

	@Bean(initMethod = "init")
	public CookbookService cookbookService(CookbookRepository cookbookRepository) {
		return new CookbookService(eventBus(), cookbookRepository);
	}

	@Bean(initMethod = "init")
	public FoodPlanService foodPlanService(FoodPlanRepository foodPlanRepository) {
		return new FoodPlanService(eventBus(), foodPlanRepository);
	}

	@Bean
	public ShoppingListService shoppingListService(ShoppingListRepository shoppingListRepository) {
		return new ShoppingListService(shoppingListRepository);
	}

	@Bean(initMethod = "init")
	public UserService userService(UserRepository userRepository) {
		return new UserService(eventBus(), userRepository);
	}

}
