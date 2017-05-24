package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class FoodPlanTest {

	@Test
	public void testClear() {
		Map<String, Meal> meals = new HashMap<>();
		meals.put("monday", new Meal("Hegarty's"));
		meals.put("tuesday", new Meal("Chili"));
		meals.put("wednesday", new Meal("Curry"));
		meals.put("thursday", new Meal("Gnotschi"));
		meals.put("friday", new Meal("Pizza"));
		meals.put("saturday", new Meal("Brot"));
		meals.put("sunday", new Meal("Salat"));
		
		FoodPlan foodPlan = new FoodPlan(meals);
		
		foodPlan.clear();
		
		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new Meal(""))
				.containsEntry("tuesday", new Meal(""))
				.containsEntry("wednesday", new Meal(""))
				.containsEntry("thursday", new Meal(""))
				.containsEntry("friday", new Meal(""))
				.containsEntry("saturday", new Meal(""))
				.containsEntry("sunday", new Meal(""));
	}

	@Test
	public void testUpdate() throws Exception {
		Map<String, Meal> changedMeals = new HashMap<>();
		changedMeals.put("monday", new Meal("Hegarty's"));
		changedMeals.put("tuesday", new Meal("Chili"));
		changedMeals.put("wednesday", new Meal("Curry"));
		changedMeals.put("thursday", new Meal("Gnotschi"));
		changedMeals.put("friday", new Meal("Pizza"));
		changedMeals.put("saturday", new Meal("Brot"));
		changedMeals.put("sunday", new Meal("Salat"));
		FoodPlan changedFoodPlan = new FoodPlan(changedMeals);
		
		Map<String, Meal> meals = new HashMap<>();
		meals.put("monday", new Meal("Hegarty's"));
		meals.put("tuesday", new Meal("Chili"));
		meals.put("wednesday", new Meal(""));
		meals.put("thursday", new Meal("Gnotschi"));
		meals.put("friday", new Meal("Pizza"));
		meals.put("saturday", new Meal("Döner"));
		meals.put("sunday", new Meal("Salat"));
		FoodPlan foodPlan = new FoodPlan(meals);
		
		foodPlan.update(changedFoodPlan);
		
		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new Meal("Hegarty's"))
				.containsEntry("tuesday", new Meal("Chili"))
				.containsEntry("wednesday", new Meal("Curry"))
				.containsEntry("thursday", new Meal("Gnotschi"))
				.containsEntry("friday", new Meal("Pizza"))
				.containsEntry("saturday", new Meal("Brot"))
				.containsEntry("sunday", new Meal("Salat"));
	}
}
