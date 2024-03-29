package household.foodplan.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import household.foodplan.domain.FoodPlanRepository;

public class DefaultFoodPlanRepositoryTest {

    @Test
    public void testDeleteCookbook() {
        FoodPlanEntityRepository foodPlanEntityRepository = mock(FoodPlanEntityRepository.class);

        FoodPlanRepository foodPlanRepository = new DefaultFoodPlanRepository(foodPlanEntityRepository, null);
        foodPlanRepository.deleteFoodPlan("6L");

        verify(foodPlanEntityRepository).deleteById("6L");
    }

}
