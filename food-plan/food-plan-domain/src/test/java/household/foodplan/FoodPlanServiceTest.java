package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class FoodPlanServiceTest {

    private FoodPlanService foodPlanService;

    @Test
    public void testGetFoodPlan() throws Exception {
        FoodPlan expected = mock(FoodPlan.class);
        FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
        when(foodPlanRepository.determineFoodPlan("1L")).thenReturn(expected);

        foodPlanService = new FoodPlanService(foodPlanRepository);
        FoodPlan actual = foodPlanService.getFoodPlan("1L");

        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void testUpdateMeal() throws Exception {
        FoodPlan saved = mock(FoodPlan.class);
        FoodPlan expected = mock(FoodPlan.class);

        FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
        when(foodPlanRepository.determineFoodPlan("1L")).thenReturn(saved);
        when(foodPlanRepository.saveFoodPlan(saved)).thenReturn(expected);

        foodPlanService = new FoodPlanService(foodPlanRepository );
        FoodPlan actual = foodPlanService.updateMeal("1L", "3L", new Recipe("2L", "17L"), new Meal(null, "name", null));

        assertThat(actual).isSameAs(expected);
        verify(saved).updateMeal("3L", new Recipe("2L", "17L"), new Meal(null, "name", null));
    }

    @Test
    public void testClear() throws Exception {
        FoodPlan foodPlan = mock(FoodPlan.class);
        FoodPlan expected = mock(FoodPlan.class);

        FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
        when(foodPlanRepository.determineFoodPlan("1L")).thenReturn(foodPlan);
        when(foodPlanRepository.saveFoodPlan(foodPlan)).thenReturn(expected);

        foodPlanService = new FoodPlanService(foodPlanRepository);
        FoodPlan actual = foodPlanService.clear("1L");

        assertThat(actual).isSameAs(expected);
        verify(foodPlan).clear();
    }

    @Test
    public void testDeleteFoodPlan() throws Exception {
        FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);

        foodPlanService = new FoodPlanService(foodPlanRepository);
        foodPlanService.deleteFoodPlan("3L");

        verify(foodPlanRepository).deleteFoodPlan("3L");
    }

}
