package household.cleaningplan;

import static household.cleaningplan.CleaningPlanAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CleaningPlanTest {

	@Test
	public void testUpdate() throws Exception {
		List<Chore> chores = new ArrayList<>();
		chores.add(new Chore("one", 1234L));
		chores.add(new Chore("two", 1235L));
		CleaningPlan cleaningPlan = new CleaningPlan(chores);
		
		cleaningPlan.update(new Chore("two", 9876L));
		assertThat(cleaningPlan)
				.hasSize(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("two").wasLastChangedAt(9876L));
	}

	@Test
	public void testRemoveChore() throws Exception {
		List<Chore> chores = new ArrayList<>();
		chores.add(createChore(1L, "one", 1234L));
		chores.add(createChore(2L, "two", 1235L));
		chores.add(createChore(3L, "three", 1236L));
		CleaningPlan cleaningPlan = new CleaningPlan(chores);
		
		cleaningPlan.removeChore(2L);
		assertThat(cleaningPlan)
				.hasSize(2)
				.chore(0, choreAssert -> choreAssert.hasName("one").wasLastChangedAt(1234L))
				.chore(1, choreAssert -> choreAssert.hasName("three").wasLastChangedAt(1236L));
	}
	
	private Chore createChore(long id, String name, long lastChanged) {
		Chore chore = spy(new Chore(name, lastChanged));
		when(chore.getId()).thenReturn(id);
		return chore;
	}

}
