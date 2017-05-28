package household.cleaningplan;

import static household.cleaningplan.ChoreAssert.assertThat;
import static household.cleaningplan.ChoreTOAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ChoreMapperTest {
	
	private ChoreMapper choreMapper;

	@Test
	public void testMap_toChoreTO() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = spy(new ChoreEntity("chore1", 12345));
		when(from.getId()).thenReturn(2L);
		
		ChoreDTO result = choreMapper.map(from);
		
		assertThat(result).hasDatabaseId(2L).hasName("chore1").wasLastChangedAt(12345);
	}
	
	@Test
	public void testMap_toChoreTO_repeatingChore_days() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = spy(new ChoreEntity("chore1", 12345, new RepeatEntity(7)));
		when(from.getId()).thenReturn(2L);
		
		ChoreDTO result = choreMapper.map(from);
		
		assertThat(result)
				.hasDatabaseId(2L)
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*24*7));
	}
	
	@Test
	public void testMap_toChoreTO_repeatingChore_hours() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = spy(new ChoreEntity("chore1", 12345, new RepeatEntity(84, TimeUnit.HOURS)));
		when(from.getId()).thenReturn(2L);
		
		ChoreDTO result = choreMapper.map(from);
		
		assertThat(result)
				.hasDatabaseId(2L)
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*84));
	}
	
	@Test
	public void testMap_toChoreTO_repeatingChore_weeks() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = spy(new ChoreEntity("chore1", 12345, new RepeatEntity(2, TimeUnit.WEEKS)));
		when(from.getId()).thenReturn(2L);
		
		ChoreDTO result = choreMapper.map(from);
		
		assertThat(result)
				.hasDatabaseId(2L)
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*24*7*2));
	}

	@Test
	public void testMap_toChore() throws Exception {
        choreMapper = new ChoreMapper();
		
		ChoreDTO from = new ChoreDTO(2L, "chore1", 12345, -1);
		ChoreEntity result = choreMapper.map(from);
		
		assertThat(result).hasName("chore1").wasLastChangedAt(12345);
	}

}
