package household.foodplan;

import static household.foodplan.MealAssert.assertThat;
import static household.foodplan.MealTOAssert.assertThat;

import org.junit.jupiter.api.Test;

public class MealDTOMapperTest {

	private MealDTOMapper mealMapper;

	@Test
	public void testMap_toMealTO() throws Exception {
		mealMapper = new MealDTOMapper();

		Meal meal = new Meal(2L, "meal1");

		MealDTO result = mealMapper.map(meal);

		assertThat(result).hasDatabaseId(2L).hasName("meal1");
	}

	@Test
	public void testMap_toMeal() throws Exception {
        mealMapper = new MealDTOMapper();

		MealDTO meal = new MealDTO(1L, "meal1", null);
		Meal result = mealMapper.map(meal);

		assertThat(result).hasName("meal1");
	}

}
