package household.cleaningplan.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class CleaningPlanServiceTest {

	private CleaningPlanService cleaningPlanService;

	@Test
	public void testGetCleaningPlan() {
		CleaningPlan expected = mock(CleaningPlan.class);
		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan("1L")).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.getCleaningPlan("1L");

		assertThat(actual).isSameAs(expected);
	}

	@Test
	public void testUpdateChore() {
		Chore input = mock(Chore.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlan saved = mock(CleaningPlan.class);
		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan("1L")).thenReturn(saved);
		when(cleaningPlanRepository.saveCleaningPlan(saved)).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.update("1L", input);

		assertThat(actual).isSameAs(expected);
		verify(saved).update(input);
	}

	@Test
	public void testAddChore() {
		CleaningPlan cleaningPlan = mock(CleaningPlan.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan("1L")).thenReturn(cleaningPlan);
		when(cleaningPlanRepository.saveCleaningPlan(cleaningPlan)).thenReturn(expected);

		Chore chore = mock(Chore.class);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.addChore("1L", chore);

		assertThat(actual).isSameAs(expected);
		verify(cleaningPlan).addChore(chore);
	}

	@Test
	public void testRemoveChore() {
		CleaningPlan cleaningPlan = mock(CleaningPlan.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan("1L")).thenReturn(cleaningPlan);
		when(cleaningPlanRepository.saveCleaningPlan(cleaningPlan)).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.removeChore("1L", "7L");

		assertThat(actual).isSameAs(expected);
		verify(cleaningPlan).removeChore("7L");
	}

	@Test
    public void testDeleteCleaningPlan() {
        CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);

	    cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
	    cleaningPlanService.deleteCleaningPlan("3L");

	    verify(cleaningPlanRepository).deleteCleaningPlan("3L");
	}

    @Test
    public void testAddTask() {
        CleaningPlan cleaningPlan = mock(CleaningPlan.class);
        CleaningPlan expected = mock(CleaningPlan.class);

        CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
        when(cleaningPlanRepository.determineCleaningPlan("1L")).thenReturn(cleaningPlan);
        when(cleaningPlanRepository.saveCleaningPlan(cleaningPlan)).thenReturn(expected);

        Task task = mock(Task.class);

        cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
        CleaningPlan actual = cleaningPlanService.addTask("1L", task);

        assertThat(actual).isSameAs(expected);
        verify(cleaningPlan).addTask(task);
    }

    public void testUpdateTask() {
		Task input = mock(Task.class);
		CleaningPlan expected = mock(CleaningPlan.class);

		CleaningPlan saved = mock(CleaningPlan.class);
		CleaningPlanRepository cleaningPlanRepository = mock(CleaningPlanRepository.class);
		when(cleaningPlanRepository.determineCleaningPlan("1L")).thenReturn(saved);
		when(cleaningPlanRepository.saveCleaningPlan(saved)).thenReturn(expected);

		cleaningPlanService = new CleaningPlanService(cleaningPlanRepository);
		CleaningPlan actual = cleaningPlanService.update("1L", input);

		assertThat(actual).isSameAs(expected);
		verify(saved).update(input);
	}

}
