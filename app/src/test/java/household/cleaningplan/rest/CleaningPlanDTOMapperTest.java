package household.cleaningplan.rest;

import household.cleaningplan.domain.Chore;
import household.cleaningplan.domain.CleaningPlan;
import org.junit.jupiter.api.Test;

import static household.cleaningplan.rest.CleaningPlanDTOAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class CleaningPlanDTOMapperTest {

    @Test
	public void testMap() {
        CleaningPlanDTOMapper cleaningPlanMapper = new CleaningPlanDTOMapper(new ChoreDTOMapper(), new TaskDTOMapper());

		Chore chore1 = new Chore("2L", "chore1", 12345);
		Chore chore2 = new Chore("3L", "chore2", 12346);

		List<Chore> chores = asList(chore1, chore2);
		CleaningPlan from = new CleaningPlan("1L", chores, new ArrayList<>());

		CleaningPlanDTO result = cleaningPlanMapper.map(from);

		assertThat(result)
		        .hasDatabaseId("1L")
		        .hasSize(2)
		        .chore(0, choreAssert -> choreAssert.hasName("chore1").wasLastChangedAt(12345))
		        .chore(1, choreAssert -> choreAssert.hasName("chore2").wasLastChangedAt(12346));
	}

}
