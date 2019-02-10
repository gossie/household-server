package household.cleaningplan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import household.household.Household;
import household.household.HouseholdDeletedEvent;

public class CleaningPlanServiceTest {

	private CleaningPlanService cleaningPlanService;

	@Test
	public void testGetCleaningPlan() throws Exception {
		CleaningPlan expected = mock(CleaningPlan.class);
		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan(1L)).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.getCleaningPlan(1L);

		assertThat(actual).isSameAs(expected);
	}

	@Test
	public void testUpdate() throws Exception {
		Chore input = mock(Chore.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlan saved = mock(CleaningPlan.class);
		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan(1L)).thenReturn(saved);
		when(cleaningPlanRepository.saveCleaningPlan(saved)).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.update(1L, input);

		assertThat(actual).isSameAs(expected);
		verify(saved).update(input);
	}

	@Test
	public void testAddChore() throws Exception {
		CleaningPlan cleaningPlan = mock(CleaningPlan.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan(1L)).thenReturn(cleaningPlan);
		when(cleaningPlanRepository.saveCleaningPlan(cleaningPlan)).thenReturn(expected);

		Chore chore = mock(Chore.class);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.addChore(1L, chore);

		assertThat(actual).isSameAs(expected);
		verify(cleaningPlan).addChore(chore);
	}

	@Test
	public void testRemoveChore() throws Exception {
		CleaningPlan cleaningPlan = mock(CleaningPlan.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan(1L)).thenReturn(cleaningPlan);
		when(cleaningPlanRepository.saveCleaningPlan(cleaningPlan)).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.removeChore(1L, 7L);

		assertThat(actual).isSameAs(expected);
		verify(cleaningPlan).removeChore(7L);
	}

	@Test
    public void testOnHouseholdDeleted() throws Exception {
	    Household household = mock(Household.class);
	    when(household.getCleaningPlanId()).thenReturn(3L);

        CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);

	    cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
	    cleaningPlanService.onHouseholdDeleted(new HouseholdDeletedEvent(household));

	    verify(cleaningPlanRepository).deleteCleaningPlan(3L);
	}

}
