package household.cleaningplan.rest;

import static household.cleaningplan.domain.ChoreAssert.assertThat;
import static household.cleaningplan.rest.ChoreDTOAssert.assertThat;

import household.cleaningplan.domain.Chore;
import household.cleaningplan.domain.Repeat;
import household.cleaningplan.domain.TimeUnit;
import org.junit.jupiter.api.Test;

public class ChoreDTOMapperTest {

    private ChoreDTOMapper choreMapper;

    @Test
    public void testMap_toChoreTO() {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore("2L", "chore1", 12345);

        ChoreDTO result = choreMapper.map(from);

        assertThat(result).hasDatabaseId("2L").hasName("chore1").wasLastChangedAt(12345);
    }

    @Test
    public void testMap_toChoreTO_repeatingChore_days() {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore("2L", "chore1", 12345, new Repeat("3L", 7));

        ChoreDTO result = choreMapper.map(from);

        assertThat(result)
                .hasDatabaseId("2L")
                .hasName("chore1")
                .wasLastChangedAt(12345)
                .isDue(12345 + (1000*60*60*24*7));
    }

    @Test
    public void testMap_toChoreTO_repeatingChore_hours() {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore("2L", "chore1", 12345, new Repeat("3L", 84, TimeUnit.HOURS));

        ChoreDTO result = choreMapper.map(from);

        assertThat(result)
                .hasDatabaseId("2L")
                .hasName("chore1")
                .wasLastChangedAt(12345)
                .isDue(12345 + (1000*60*60*84));
    }

    @Test
    public void testMap_toChoreTO_repeatingChore_weeks() {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore("2L", "chore1", 12345, new Repeat("3L", 2, TimeUnit.WEEKS));

        ChoreDTO result = choreMapper.map(from);

        assertThat(result)
                .hasDatabaseId("2L")
                .hasName("chore1")
                .wasLastChangedAt(12345)
                .isDue(12345 + (1000*60*60*24*7*2));
    }

    @Test
    public void testMap_toChore() {
        choreMapper = new ChoreDTOMapper();

        ChoreDTO from = new ChoreDTO("2L", "chore1", 12345, -1, 0);
        Chore result = choreMapper.map(from);

        assertThat(result).hasName("chore1").wasLastChangedAt(12345);
    }

}
