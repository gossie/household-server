package household.cleaningplan.persistence;

import static household.cleaningplan.domain.ChoreAssert.assertThat;
import static household.cleaningplan.persistence.ChoreEntityAssert.assertThat;

import household.cleaningplan.domain.Chore;
import org.junit.jupiter.api.Test;

public class ChoreMapperTest {

	private ChoreMapper choreMapper;

	@Test
	public void testMap_toChoreTO() {
		choreMapper = new ChoreMapper();

		ChoreEntity from = new ChoreEntity("2L", "chore1", 12345, null);

		Chore result = choreMapper.map(from);

		assertThat(result).hasId("2L").hasName("chore1").wasLastChangedAt(12345);
	}

	@Test
	public void testMap_toChoreTO_repeatingChore_days() {
		choreMapper = new ChoreMapper();

		ChoreEntity from = new ChoreEntity("2L", "chore1", 12345, new RepeatEntity("11L", 7));

		Chore result = choreMapper.map(from);

		assertThat(result)
				.hasId("2L")
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*24*7));
	}

	@Test
	public void testMap_toChoreTO_repeatingChore_hours() {
		choreMapper = new ChoreMapper();

		ChoreEntity from = new ChoreEntity("2L", "chore1", 12345, new RepeatEntity("11L", 84, TimeUnit.HOURS));

		Chore result = choreMapper.map(from);

		assertThat(result)
				.hasId("2L")
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*84));
	}

	@Test
	public void testMap_toChoreTO_repeatingChore_weeks() {
		choreMapper = new ChoreMapper();

		ChoreEntity from = new ChoreEntity("2L", "chore1", 12345, new RepeatEntity("10L", 2, TimeUnit.WEEKS));

		Chore result = choreMapper.map(from);

		assertThat(result)
				.hasId("2L")
				.hasName("chore1")
				.wasLastChangedAt(12345)
				.isDue(12345 + (1000*60*60*24*7*2));
	}

	@Test
	public void testMap_toChore() {
        choreMapper = new ChoreMapper();

		Chore from = new Chore("2L", "chore1", 12345);
		ChoreEntity result = choreMapper.map(from);

		assertThat(result).hasName("chore1").wasLastChangedAt(12345);
	}

}
