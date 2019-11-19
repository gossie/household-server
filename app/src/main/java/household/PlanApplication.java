package household;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
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

@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
@EnableBinding(HouseholdMessageChannels.class)
public class PlanApplication {

    public static void main(String[] args) {
		SpringApplication.run(PlanApplication.class, args);
	}

	@Bean
	public HouseholdService householdService(HouseholdRepository householdRepository) {
		return new HouseholdService(householdRepository);
	}

	@Bean
	public CleaningPlanService cleaningPlanService(CleaningPlanRepository cleaningPlanRepository) {
		return new CleaningPlanService(cleaningPlanRepository);
	}

	@Bean
	public CookbookService cookbookService(CookbookRepository cookbookRepository) {
		return new CookbookService(cookbookRepository);
	}

	@Bean
	public FoodPlanService foodPlanService(FoodPlanRepository foodPlanRepository) {
		return new FoodPlanService(foodPlanRepository);
	}

	@Bean
	public ShoppingListService shoppingListService(ShoppingListRepository shoppingListRepository) {
		return new ShoppingListService(shoppingListRepository);
	}

	@Bean
	public UserService userService(UserRepository userRepository) {
		return new UserService(userRepository);
	}

	@Bean
    public LogAspect logAspect() {
	    return new LogAspect();
    }
}
