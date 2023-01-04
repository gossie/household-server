package household.cleaningplan.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static household.cleaningplan.domain.CleaningPlanAssert.assertThat;

public class CleaningPlanTest {

	@Test
	public void testUpdateChore() throws Exception {
		List<Chore> chores = new ArrayList<>();
		chores.add(new Chore("1L", "one", 1234L));
		chores.add(new Chore("2L", "two", 1235L));
		CleaningPlan cleaningPlan = new CleaningPlan("3L", chores, Collections.emptyList());

		cleaningPlan.update(new Chore("2L", "two", 9876L));
		assertThat(cleaningPlan)
				.numberOfChores(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("two").wasLastChangedAt(9876L));
	}

	@Test
	public void testRemoveChore() throws Exception {
		List<Chore> chores = new ArrayList<>();
		chores.add(createChore("1L", "one", 1234L));
		chores.add(createChore("2L", "two", 1235L));
		chores.add(createChore("3L", "three", 1236L));
		CleaningPlan cleaningPlan = new CleaningPlan("4L", chores, Collections.emptyList());

		cleaningPlan.removeChore("2L");
		assertThat(cleaningPlan)
				.numberOfChores(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("three").wasLastChangedAt(1236L));
	}

	@Test
	public void testUpdateTask() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task("1L", "one", true));
		tasks.add(new Task("2L", "two", false));
		CleaningPlan cleaningPlan = new CleaningPlan("3L", Collections.emptyList(), tasks);

		cleaningPlan.update(new Task("2L", "two", true));
		assertThat(cleaningPlan)
				.numberOfTasks(2)
				.task(0, taskAssert -> taskAssert.hasName("one").isDone())
				.task(1, taskAssert -> taskAssert.hasName("two").isDone());
	}

	private Chore createChore(String id, String name, long lastChanged) {
		return new Chore(id, name, lastChanged);
	}

}
