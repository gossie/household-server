package household.foodplan;

import static household.foodplan.FoodPlanAssert.assertThat;
import static household.foodplan.FoodPlanEntityAssert.assertThat;
import static household.foodplan.MealAssert.assertThat;
import static household.foodplan.MealEntityAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FoodPlanMapperTest {

	private FoodPlanMapper foodPlanMapper;

	@Test
	public void testMap_toFoodPlanDTO() throws Exception {
		foodPlanMapper = new FoodPlanMapper(new MealMapper());

		MealEntity meal1 = new MealEntity(3L, "meal1");
		MealEntity meal2 = new MealEntity(4L, "meal2");

		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("one", meal1);
		meals.put("two", meal2);

		FoodPlanEntity foodPlan = new FoodPlanEntity(2L, meals);

		FoodPlan result = foodPlanMapper.map(foodPlan);

		assertThat(result)
		    .hasId(2L)
		    .hasSize(2);
		assertThat(result.getMeals().get("one")).hasId(3L).hasName("meal1");
		assertThat(result.getMeals().get("two")).hasId(4L).hasName("meal2");
	}

	@Test
	public void testMap_toFoodPlan() throws Exception {
		foodPlanMapper = new FoodPlanMapper(new MealMapper());

		Map<String, Meal> meals = new HashMap<>();
		meals.put("one", new Meal(1L, "meal1"));
		meals.put("two", new Meal(2L, "meal2"));
		FoodPlan foodPlan = new FoodPlan(1L, meals);

		FoodPlanEntity result = foodPlanMapper.map(foodPlan);

		assertThat(result).hasSize(2);
		assertThat(result.getMeals().get("one")).hasName("meal1");
		assertThat(result.getMeals().get("two")).hasName("meal2");
	}

}
