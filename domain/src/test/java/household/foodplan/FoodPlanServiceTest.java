package household.foodplan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class FoodPlanServiceTest {
	
	private FoodPlanService foodPlanService;

	@Test
	public void testGetFoodPlan() throws Exception {
		FoodPlan expected = mock(FoodPlan.class);
		FoodPlanRepository foodPlanRepository = mock(FoodPlanRepository.class);
		when(foodPlanRepository.determineFoodPlan(1L)).thenReturn(expected);
		
		foodPlanService = new FoodPlanService(foodPlanRepository);
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
		
		foodPlanService = new FoodPlanService(foodPlanRepository );
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
		
		foodPlanService = new FoodPlanService(foodPlanRepository);
		FoodPlan actual = foodPlanService.clear(1L);
		
		assertThat(actual).isSameAs(expected);
		verify(foodPlan).clear();
	}

}
