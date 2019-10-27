package household.cleaningplan;

import static household.cleaningplan.CleaningPlanAssert.assertThat;
import static household.cleaningplan.CleaningPlanEntityAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CleaningPlanMapperTest {

	private CleaningPlanMapper cleaningPlanMapper;

	@Test
	public void testMap() throws Exception {
		cleaningPlanMapper = new CleaningPlanMapper(new ChoreMapper());

		ChoreEntity chore1 = new ChoreEntity(2L, "chore1", 12345, null);
		ChoreEntity chore2 = new ChoreEntity(3L, "chore2", 12346, null);

		List<ChoreEntity> chores = asList(chore1, chore2);
		CleaningPlanEntity from = new CleaningPlanEntity(1L, chores);

		CleaningPlan result = cleaningPlanMapper.map(from);

		assertThat(result)
		        .hasId(1L)
		        .hasSize(2)
		        .chore(0, choreAssert -> choreAssert.hasName("chore1").wasLastChangedAt(12345))
		        .chore(1, choreAssert -> choreAssert.hasName("chore2").wasLastChangedAt(12346));
	}

	@Test
	public void testMapCleaningPlan() throws Exception {
        cleaningPlanMapper = new CleaningPlanMapper(new ChoreMapper());

		Chore chore1 = new Chore(2L, "chore1", 12345, null);
		Chore chore2 = new Chore(3L, "chore2", 12346, null);

		List<Chore> chores = asList(chore1, chore2);
		CleaningPlan from = new CleaningPlan(1L, chores);

		CleaningPlanEntity result = cleaningPlanMapper.map(from);

		assertThat(result)
		        .hasId(1L)
		        .hasSize(2)
		        .chore(0, choreAssert -> choreAssert.hasName("chore1").wasLastChangedAt(12345))
		        .chore(1, choreAssert -> choreAssert.hasName("chore2").wasLastChangedAt(12346));
	}

}
