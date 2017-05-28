package household.cleaningplan;

import static household.cleaningplan.ChoreAssert.assertThat;
import static household.cleaningplan.ChoreEntityAssert.assertThat;

import org.junit.Test;

public class ChoreMapperTest {
	
	private ChoreMapper choreMapper;

	@Test
	public void testMap_toChoreTO() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = new ChoreEntity(2L, "chore1", 12345, null);
		
		Chore result = choreMapper.map(from);
		
		assertThat(result).hasId(2L).hasName("chore1").wasLastChangedAt(12345);
	}
	
	@Test
	public void testMap_toChoreTO_repeatingChore_days() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = new ChoreEntity(2L, "chore1", 12345, new RepeatEntity(11L, 7));
		
		Chore result = choreMapper.map(from);
		
		assertThat(result)
				.hasId(2L)
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*24*7));
	}
	
	@Test
	public void testMap_toChoreTO_repeatingChore_hours() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = new ChoreEntity(2L, "chore1", 12345, new RepeatEntity(11L, 84, TimeUnit.HOURS));
		
		Chore result = choreMapper.map(from);
		
		assertThat(result)
				.hasId(2L)
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*84));
	}
	
	@Test
	public void testMap_toChoreTO_repeatingChore_weeks() throws Exception {
		choreMapper = new ChoreMapper();
		
		ChoreEntity from = new ChoreEntity(2L, "chore1", 12345, new RepeatEntity(10L, 2, TimeUnit.WEEKS));
		
		Chore result = choreMapper.map(from);
		
		assertThat(result)
				.hasId(2L)
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*24*7*2));
	}

	@Test
	public void testMap_toChore() throws Exception {
        choreMapper = new ChoreMapper();
		
		Chore from = new Chore(2L, "chore1", 12345);
		ChoreEntity result = choreMapper.map(from);
		
		assertThat(result).hasName("chore1").wasLastChangedAt(12345);
	}

}
