package household.foodplan;

import static household.foodplan.MealAssert.assertThat;
import static household.foodplan.MealEntityAssert.assertThat;

import org.junit.Test;

public class MealMapperTest {
	
	private MealMapper mealMapper;

	@Test
	public void testMap_toMealTO() throws Exception {
		mealMapper = new MealMapper();
		
		MealEntity meal = new MealEntity(2L, "meal1");
		
		Meal result = mealMapper.map(meal);
		
		assertThat(result).hasId(2L).hasName("meal1");
	}

	@Test
	public void testMap_toMeal() throws Exception {
        mealMapper = new MealMapper();
		
		Meal meal = new Meal(1L, "meal1");
		MealEntity result = mealMapper.map(meal);
		
		assertThat(result).hasName("meal1");
	}

}
