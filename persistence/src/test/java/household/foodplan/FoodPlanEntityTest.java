package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class FoodPlanEntityTest {

	@Test
	public void testClear() {
		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("monday", new MealEntity(1L, "Hegarty's"));
		meals.put("tuesday", new MealEntity(2L, "Chili"));
		meals.put("wednesday", new MealEntity(3L, "Curry"));
		meals.put("thursday", new MealEntity(4L, "Gnotschi"));
		meals.put("friday", new MealEntity(5L, "Pizza"));
		meals.put("saturday", new MealEntity(6L, "Brot"));
		meals.put("sunday", new MealEntity(7L, "Salat"));
		
		FoodPlanEntity foodPlan = new FoodPlanEntity(8L, meals);
		
		foodPlan.clear();
		
		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new MealEntity(1L, ""))
				.containsEntry("tuesday", new MealEntity(2L, ""))
				.containsEntry("wednesday", new MealEntity(3L, ""))
				.containsEntry("thursday", new MealEntity(4L, ""))
				.containsEntry("friday", new MealEntity(5L, ""))
				.containsEntry("saturday", new MealEntity(6L, ""))
				.containsEntry("sunday", new MealEntity(7L, ""));
	}

	@Test
	public void testUpdate() throws Exception {
		Map<String, MealEntity> changedMeals = new HashMap<>();
		changedMeals.put("monday", new MealEntity(1L, "Hegarty's"));
		changedMeals.put("tuesday", new MealEntity(2L, "Chili"));
		changedMeals.put("wednesday", new MealEntity(3L, "Curry"));
		changedMeals.put("thursday", new MealEntity(4L, "Gnotschi"));
		changedMeals.put("friday", new MealEntity(5L, "Pizza"));
		changedMeals.put("saturday", new MealEntity(6L, "Brot"));
		changedMeals.put("sunday", new MealEntity(7L, "Salat"));
		FoodPlanEntity changedFoodPlan = new FoodPlanEntity(8L, changedMeals);
		
		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("monday", new MealEntity(1L, "Hegarty's"));
		meals.put("tuesday", new MealEntity(2L, "Chili"));
		meals.put("wednesday", new MealEntity(3L, ""));
		meals.put("thursday", new MealEntity(4L, "Gnotschi"));
		meals.put("friday", new MealEntity(5L, "Pizza"));
		meals.put("saturday", new MealEntity(6L, "Döner"));
		meals.put("sunday", new MealEntity(7L, "Salat"));
		FoodPlanEntity foodPlan = new FoodPlanEntity(8L, meals);
		
		foodPlan.update(changedFoodPlan);
		
		assertThat(foodPlan.getMeals())
				.hasSize(7)
				.containsEntry("monday", new MealEntity(1L, "Hegarty's"))
				.containsEntry("tuesday", new MealEntity(2L, "Chili"))
				.containsEntry("wednesday", new MealEntity(3L, "Curry"))
				.containsEntry("thursday", new MealEntity(4L, "Gnotschi"))
				.containsEntry("friday", new MealEntity(5L, "Pizza"))
				.containsEntry("saturday", new MealEntity(6L, "Brot"))
				.containsEntry("sunday", new MealEntity(7L, "Salat"));
	}
}
