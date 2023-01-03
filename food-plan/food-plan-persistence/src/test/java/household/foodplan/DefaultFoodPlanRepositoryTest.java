package household.foodplan;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class DefaultFoodPlanRepositoryTest {

    @Test
    public void testDeleteCookbook() {
        FoodPlanEntityRepository foodPlanEntityRepository = mock(FoodPlanEntityRepository.class);

        FoodPlanRepository foodPlanRepository = new DefaultFoodPlanRepository(foodPlanEntityRepository, null);
        foodPlanRepository.deleteFoodPlan("6L");

        verify(foodPlanEntityRepository).deleteById("6L");
    }

}
