package household.foodplan;

import static household.foodplan.FoodPlanAssert.assertThat;
import static household.foodplan.FoodPlanTOAssert.assertThat;
import static household.foodplan.MealAssert.assertThat;
import static household.foodplan.MealTOAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FoodPlanDTOMapperTest {

    private FoodPlanDTOMapper foodPlanMapper;

    @Test
    public void testMap_toFoodPlanDTO() throws Exception {
        foodPlanMapper = new FoodPlanDTOMapper(new MealDTOMapper());

        Meal meal1 = new Meal("3L", "meal1");
        Meal meal2 = new Meal("4L", "meal2");

        Map<String, Meal> meals = new HashMap<>();
        meals.put("one", meal1);
        meals.put("two", meal2);

        FoodPlan foodPlan = new FoodPlan("2L", meals);

        FoodPlanDTO result = foodPlanMapper.map(foodPlan);

        assertThat(result).hasDatabaseId("2L").hasSize(2);
        assertThat(result.getMeals().get("one")).hasDatabaseId("3L").hasName("meal1");
        assertThat(result.getMeals().get("two")).hasDatabaseId("4L").hasName("meal2");
    }

    @Test
    public void testMap_toFoodPlan() throws Exception {
        foodPlanMapper = new FoodPlanDTOMapper(new MealDTOMapper());

        Map<String, MealDTO> meals = new HashMap<>();
        meals.put("one", new MealDTO("1L", "meal1", null, null));
        meals.put("two", new MealDTO("2L", "meal2", null, null));
        FoodPlanDTO foodPlan = new FoodPlanDTO("1L", meals);

        FoodPlan result = foodPlanMapper.map(foodPlan);

        assertThat(result).hasSize(2);
        assertThat(result.getMeals().get("one")).hasName("meal1");
        assertThat(result.getMeals().get("two")).hasName("meal2");
    }

}
