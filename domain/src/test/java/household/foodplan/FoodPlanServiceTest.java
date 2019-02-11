package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.google.common.eventbus.EventBus;
import household.household.Household;
import household.household.HouseholdDeletedEvent;

public class FoodPlanServiceTest {

	private FoodPlanService foodPlanService;

	@Test
	public void testGetFoodPlan() throws Exception {
		FoodPlan expected = mock(FoodPlan.class);
		FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
		when(foodPlanRepository.determineFoodPlan(1L)).thenReturn(expected);

		foodPlanService = new FoodPlanService(mock(EventBus.class), foodPlanRepository);
		FoodPlan actual = foodPlanService.getFoodPlan(1L);

		assertThat(actual).isSameAs(expected);
	}

	@Test
	public void testUpdate() throws Exception {
		FoodPlan input = mock(FoodPlan.class);
		FoodPlan saved = mock(FoodPlan.class);
		FoodPlan expected = mock(FoodPlan.class);

		FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
		when(foodPlanRepository.determineFoodPlan(1L)).thenReturn(saved);
		when(foodPlanRepository.saveFoodPlan(saved)).thenReturn(expected);

		foodPlanService = new FoodPlanService(mock(EventBus.class), foodPlanRepository );
		FoodPlan actual = foodPlanService.update(1L, input);

		assertThat(actual).isSameAs(expected);
		verify(saved).update(input);
	}

	@Test
	public void testClear() throws Exception {
		FoodPlan foodPlan = mock(FoodPlan.class);
		FoodPlan expected = mock(FoodPlan.class);

		FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
		when(foodPlanRepository.determineFoodPlan(1L)).thenReturn(foodPlan);
		when(foodPlanRepository.saveFoodPlan(foodPlan)).thenReturn(expected);

		foodPlanService = new FoodPlanService(mock(EventBus.class), foodPlanRepository);
		FoodPlan actual = foodPlanService.clear(1L);

		assertThat(actual).isSameAs(expected);
		verify(foodPlan).clear();
	}

    @Test
    public void testOnHouseholdDeleted() throws Exception {
        Household household = mock(Household.class);
        when(household.getFoodPlanId()).thenReturn(3L);

        FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);

        foodPlanService = new FoodPlanService(mock(EventBus.class), foodPlanRepository);
        foodPlanService.onHouseholdDeleted(new HouseholdDeletedEvent(household));

        verify(foodPlanRepository).deleteFoodPlan(3L);
    }

}
