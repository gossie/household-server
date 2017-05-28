package household.cleaningplan;

import static household.cleaningplan.CleaningPlanEntityAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CleaningPlanEntityTest {

	@Test
	public void testUpdate() throws Exception {
		List<ChoreEntity> chores = new ArrayList<>();
		chores.add(new ChoreEntity(1L, "one", 1234L, null));
		chores.add(new ChoreEntity(2L, "two", 1235L, null));
		CleaningPlanEntity cleaningPlan = new CleaningPlanEntity(9L, chores);
		
		cleaningPlan.update(new ChoreEntity(2L, "two", 9876L, null));
		assertThat(cleaningPlan)
				.hasSize(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("two").wasLastChangedAt(9876L));
	}

	@Test
	public void testRemoveChore() throws Exception {
		List<ChoreEntity> chores = new ArrayList<>();
		chores.add(createChore(1L, "one", 1234L));
		chores.add(createChore(2L, "two", 1235L));
		chores.add(createChore(3L, "three", 1236L));
		CleaningPlanEntity cleaningPlan = new CleaningPlanEntity(7L, chores);
		
		cleaningPlan.removeChore(2L);
		assertThat(cleaningPlan)
				.hasSize(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("three").wasLastChangedAt(1236L));
	}
	
	private ChoreEntity createChore(long id, String name, long lastChanged) {
		return new ChoreEntity(id, name, lastChanged, null);
	}

}
