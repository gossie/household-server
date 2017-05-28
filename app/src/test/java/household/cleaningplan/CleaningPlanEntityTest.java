package household.cleaningplan;

import static household.cleaningplan.CleaningPlanAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CleaningPlanEntityTest {

	@Test
	public void testUpdate() throws Exception {
		List<ChoreEntity> chores = new ArrayList<>();
		chores.add(new ChoreEntity("one", 1234L));
		chores.add(new ChoreEntity("two", 1235L));
		CleaningPlanEntity cleaningPlan = new CleaningPlanEntity(chores);
		
		cleaningPlan.update(new ChoreEntity("two", 9876L));
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
		CleaningPlanEntity cleaningPlan = new CleaningPlanEntity(chores);
		
		cleaningPlan.removeChore(2L);
		assertThat(cleaningPlan)
				.hasSize(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("three").wasLastChangedAt(1236L));
	}
	
	private ChoreEntity createChore(long id, String name, long lastChanged) {
		ChoreEntity chore = spy(new ChoreEntity(name, lastChanged));
		when(chore.getId()).thenReturn(id);
		return chore;
	}

}
