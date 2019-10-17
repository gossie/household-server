package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


public class FoodPlanTest {

	@Test
	public void testClear() {
		Map<String, Meal> meals = new HashMap<>();
		meals.put("monday", new Meal(1L, "Hegarty's"));
		meals.put("tuesday", new Meal(2L, "Chili"));
		meals.put("wednesday", new Meal(3L, "Curry"));
		meals.put("thursday", new Meal(4L, "Gnotschi"));
		meals.put("friday", new Meal(5L, "Pizza"));
		meals.put("saturday", new Meal(6L, "Brot"));
		meals.put("sunday", new Meal(7L, "Salat"));

		FoodPlan foodPlan = new FoodPlan(8L, meals);

		foodPlan.clear();

		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new Meal(1L, ""))
				.containsEntry("tuesday", new Meal(2L, ""))
				.containsEntry("wednesday", new Meal(3L, ""))
				.containsEntry("thursday", new Meal(4L, ""))
				.containsEntry("friday", new Meal(5L, ""))
				.containsEntry("saturday", new Meal(6L, ""))
				.containsEntry("sunday", new Meal(7L, ""));
	}

	@Test
	public void testUpdate() throws Exception {
		Map<String, Meal> changedMeals = new HashMap<>();
		changedMeals.put("monday", new Meal(1L, "Hegarty's"));
		changedMeals.put("tuesday", new Meal(2L, "Chili"));
		changedMeals.put("wednesday", new Meal(3L, "Curry"));
		changedMeals.put("thursday", new Meal(4L, "Gnotschi"));
		changedMeals.put("friday", new Meal(5L, "Pizza"));
		changedMeals.put("saturday", new Meal(6L, "Brot"));
		changedMeals.put("sunday", new Meal(7L, "Salat"));
		FoodPlan changedFoodPlan = new FoodPlan(8L, changedMeals);

		Map<String, Meal> meals = new HashMap<>();
		meals.put("monday", new Meal(1L, "Hegarty's"));
		meals.put("tuesday", new Meal(2L, "Chili"));
		meals.put("wednesday", new Meal(3L, ""));
		meals.put("thursday", new Meal(4L, "Gnotschi"));
		meals.put("friday", new Meal(5L, "Pizza"));
		meals.put("saturday", new Meal(6L, "Döner"));
		meals.put("sunday", new Meal(7L, "Salat"));
		FoodPlan foodPlan = new FoodPlan(8L, meals);

		foodPlan.update(changedFoodPlan);

		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new Meal(1L, "Hegarty's"))
				.containsEntry("tuesday", new Meal(2L, "Chili"))
				.containsEntry("wednesday", new Meal(3L, "Curry"))
				.containsEntry("thursday", new Meal(4L, "Gnotschi"))
				.containsEntry("friday", new Meal(5L, "Pizza"))
				.containsEntry("saturday", new Meal(6L, "Brot"))
				.containsEntry("sunday", new Meal(7L, "Salat"));
	}
}
