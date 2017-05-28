package household.foodplan;

import static household.foodplan.MealAssert.assertThat;
import static household.foodplan.MealTOAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class MealMapperTest {
	
	private MealMapper mealMapper;

	@Test
	public void testMap_toMealTO() throws Exception {
		mealMapper = new MealMapper();
		
		MealEntity meal = spy(new MealEntity("meal1"));
		when(meal.getId()).thenReturn(2L);
		
		MealDTO result = mealMapper.map(meal);
		
		assertThat(result).hasDatabaseId(2L).hasName("meal1");
	}

	@Test
	public void testMap_toMeal() throws Exception {
        mealMapper = new MealMapper();
		
		MealDTO meal = new MealDTO(1L, "meal1");
		MealEntity result = mealMapper.map(meal);
		
		assertThat(result).hasName("meal1");
	}

}
