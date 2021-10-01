package household.cleaningplan;

import static household.cleaningplan.CleaningPlanAssert.assertThat;
import static household.cleaningplan.CleaningPlanEntityAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CleaningPlanMapperTest {

	private CleaningPlanMapper cleaningPlanMapper;

	@Test
	public void testMap() {
		cleaningPlanMapper = new CleaningPlanMapper(new ChoreMapper(), new TaskMapper());

        ChoreEntity chore1 = new ChoreEntity(2L, "chore1", 12345, null);
        ChoreEntity chore2 = new ChoreEntity(3L, "chore2", 12346, null);

        TaskEntity task1 = new TaskEntity(2L, "task1", true);
        TaskEntity task2 = new TaskEntity(3L, "task2", false);

		List<ChoreEntity> chores = asList(chore1, chore2);
        List<TaskEntity> tasks = asList(task1, task2);
		CleaningPlanEntity from = new CleaningPlanEntity(1L, chores, tasks);

		CleaningPlan result = cleaningPlanMapper.map(from);

		assertThat(result)
		        .hasId(1L)
                .numberOfChores(2)
                .chore(0, choreAssert -> choreAssert.hasName("chore1").wasLastChangedAt(12345))
                .chore(1, choreAssert -> choreAssert.hasName("chore2").wasLastChangedAt(12346))
                .numberOfTasks(2)
                .task(0, taskAssert -> taskAssert.hasName("task1").isDone())
                .task(1, taskAssert -> taskAssert.hasName("task2").isNotDone());
	}

	@Test
	public void testMapCleaningPlan() {
        cleaningPlanMapper = new CleaningPlanMapper(new ChoreMapper(), new TaskMapper());

        Chore chore1 = new Chore(2L, "chore1", 12345, null);
        Chore chore2 = new Chore(3L, "chore2", 12346, null);
        List<Chore> chores = asList(chore1, chore2);

        Task task1 = new Task(2L, "task1", true);
        Task task2 = new Task(3L, "task2", false);
        List<Task> tasks = asList(task1, task2);

		CleaningPlan from = new CleaningPlan(1L, chores, tasks);

		CleaningPlanEntity result = cleaningPlanMapper.map(from);

		assertThat(result)
		        .hasId(1L)
		        .hasSize(2)
		        .chore(0, choreAssert -> choreAssert.hasName("chore1").wasLastChangedAt(12345))
		        .chore(1, choreAssert -> choreAssert.hasName("chore2").wasLastChangedAt(12346));
	}

}
