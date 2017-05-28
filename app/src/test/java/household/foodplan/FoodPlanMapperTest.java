package household.foodplan;

import static household.foodplan.FoodPlanAssert.assertThat;
import static household.foodplan.FoodPlanTOAssert.assertThat;
import static household.foodplan.MealAssert.assertThat;
import static household.foodplan.MealTOAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FoodPlanMapperTest {
	
	private FoodPlanMapper foodPlanMapper;

	@Test
	public void testMap_toFoodPlanDTO() throws Exception {
		foodPlanMapper = new FoodPlanMapper(new MealMapper());

		MealEntity meal1 = spy(new MealEntity("meal1"));
		when(meal1.getId()).thenReturn(3L);
		MealEntity meal2 = spy(new MealEntity("meal2"));
		when(meal2.getId()).thenReturn(4L);
		
		Map<String, MealEntity> meals = new HashMap<>();
		meals.put("one", meal1);
		meals.put("two", meal2);
		
		FoodPlanEntity foodPlan = spy(new FoodPlanEntity(meals));
		when(foodPlan.getId()).thenReturn(2L);
		
		FoodPlanDTO result = foodPlanMapper.map(foodPlan);
		
		assertThat(result).hasDatabaseId(2L).hasSize(2);
		assertThat(result.getMeals().get("one")).hasDatabaseId(3L).hasName("meal1");
		assertThat(result.getMeals().get("two")).hasDatabaseId(4L).hasName("meal2");
	}

	@Test
	public void testMap_toFoodPlan() throws Exception {
		foodPlanMapper = new FoodPlanMapper(new MealMapper());
		
		Map<String, MealDTO> meals = new HashMap<>();
		meals.put("one", new MealDTO(1L, "meal1"));
		meals.put("two", new MealDTO(2L, "meal2"));
		FoodPlanDTO foodPlan = new FoodPlanDTO(1L, meals);
		
		FoodPlanEntity result = foodPlanMapper.map(foodPlan);
		
		assertThat(result).hasSize(2);
		assertThat(result.getMeals().get("one")).hasName("meal1");
		assertThat(result.getMeals().get("two")).hasName("meal2");
	}

}
