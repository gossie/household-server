package household.foodplan.persistence;

import household.foodplan.domain.FoodPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodPlanPersistenceContext {

	@Autowired
	private FoodPlanEntityRepository foodPlanEntityRepository;

	private MealMapper mealMapper() {
		return new MealMapper();
	}

	@Bean
	public FoodPlanMapper foodPlanMapper() {
		return new FoodPlanMapper(mealMapper());
	}

	@Bean
	public FoodPlanRepository foodPlanRepository() {
		return new DefaultFoodPlanRepository(foodPlanEntityRepository, foodPlanMapper());
	}
}
