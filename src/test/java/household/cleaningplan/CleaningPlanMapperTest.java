package household.cleaningplan;
import static household.cleaningplan.CleaningPlanDTOAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class CleaningPlanMapperTest {
	
	private CleaningPlanMapper cleaningPlanMapper;

	@Test
	public void testMap() throws Exception {
		cleaningPlanMapper = new CleaningPlanMapper(new ChoreMapper());
		
		Chore chore1 = spy(new Chore("chore1", 12345));
		when(chore1.getId()).thenReturn(2L);
		Chore chore2 = spy(new Chore("chore2", 12346));
		when(chore2.getId()).thenReturn(3L);
		
		List<Chore> chores = asList(chore1, chore2);
		CleaningPlan from = spy(new CleaningPlan(chores));
		when(from.getId()).thenReturn(1L);
		
		CleaningPlanDTO result = cleaningPlanMapper.map(from);
		
		assertThat(result)
		        .hasDatabaseId(1L)
		        .hasSize(2)
		        .chore(0, choreAssert -> choreAssert.hasName("chore1").wasLastChangedAt(12345))
		        .chore(1, choreAssert -> choreAssert.hasName("chore2").wasLastChangedAt(12346));
	}

}
