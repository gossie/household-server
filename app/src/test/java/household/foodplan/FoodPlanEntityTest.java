package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class FoodPlanEntityTest {

	@Test
	public void testClear() {
		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("monday", new MealEntity("Hegarty's"));
		meals.put("tuesday", new MealEntity("Chili"));
		meals.put("wednesday", new MealEntity("Curry"));
		meals.put("thursday", new MealEntity("Gnotschi"));
		meals.put("friday", new MealEntity("Pizza"));
		meals.put("saturday", new MealEntity("Brot"));
		meals.put("sunday", new MealEntity("Salat"));
		
		FoodPlanEntity foodPlan = new FoodPlanEntity(meals);
		
		foodPlan.clear();
		
		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new MealEntity(""))
				.containsEntry("tuesday", new MealEntity(""))
				.containsEntry("wednesday", new MealEntity(""))
				.containsEntry("thursday", new MealEntity(""))
				.containsEntry("friday", new MealEntity(""))
				.containsEntry("saturday", new MealEntity(""))
				.containsEntry("sunday", new MealEntity(""));
	}

	@Test
	public void testUpdate() throws Exception {
		Map<String, MealEntity> changedMeals = new HashMap<>();
		changedMeals.put("monday", new MealEntity("Hegarty's"));
		changedMeals.put("tuesday", new MealEntity("Chili"));
		changedMeals.put("wednesday", new MealEntity("Curry"));
		changedMeals.put("thursday", new MealEntity("Gnotschi"));
		changedMeals.put("friday", new MealEntity("Pizza"));
		changedMeals.put("saturday", new MealEntity("Brot"));
		changedMeals.put("sunday", new MealEntity("Salat"));
		FoodPlanEntity changedFoodPlan = new FoodPlanEntity(changedMeals);
		
		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("monday", new MealEntity("Hegarty's"));
		meals.put("tuesday", new MealEntity("Chili"));
		meals.put("wednesday", new MealEntity(""));
		meals.put("thursday", new MealEntity("Gnotschi"));
		meals.put("friday", new MealEntity("Pizza"));
		meals.put("saturday", new MealEntity("Döner"));
		meals.put("sunday", new MealEntity("Salat"));
		FoodPlanEntity foodPlan = new FoodPlanEntity(meals);
		
		foodPlan.update(changedFoodPlan);
		
		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new MealEntity("Hegarty's"))
				.containsEntry("tuesday", new MealEntity("Chili"))
				.containsEntry("wednesday", new MealEntity("Curry"))
				.containsEntry("thursday", new MealEntity("Gnotschi"))
				.containsEntry("friday", new MealEntity("Pizza"))
				.containsEntry("saturday", new MealEntity("Brot"))
				.containsEntry("sunday", new MealEntity("Salat"));
	}
}
