package household.cleaningplan;

import static household.cleaningplan.ChoreAssert.assertThat;
import static household.cleaningplan.ChoreTOAssert.assertThat;

import org.junit.jupiter.api.Test;

public class ChoreDTOMapperTest {

    private ChoreDTOMapper choreMapper;

    @Test
    public void testMap_toChoreTO() throws Exception {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore(2L, "chore1", 12345);

        ChoreDTO result = choreMapper.map(from);

        assertThat(result).hasDatabaseId(2L).hasName("chore1").wasLastChangedAt(12345);
    }

    @Test
    public void testMap_toChoreTO_repeatingChore_days() throws Exception {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore(2L, "chore1", 12345, new Repeat(3L, 7));

        ChoreDTO result = choreMapper.map(from);

        assertThat(result)
                .hasDatabaseId(2L)
                .hasName("chore1")
                .wasLastChangedAt(12345)
                .isDue(12345 + (1000*60*60*24*7));
    }

    @Test
    public void testMap_toChoreTO_repeatingChore_hours() throws Exception {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore(2L, "chore1", 12345, new Repeat(3L, 84, TimeUnit.HOURS));

        ChoreDTO result = choreMapper.map(from);

        assertThat(result)
                .hasDatabaseId(2L)
                .hasName("chore1")
                .wasLastChangedAt(12345)
                .isDue(12345 + (1000*60*60*84));
    }

    @Test
    public void testMap_toChoreTO_repeatingChore_weeks() throws Exception {
        choreMapper = new ChoreDTOMapper();

        Chore from = new Chore(2L, "chore1", 12345, new Repeat(3L, 2, TimeUnit.WEEKS));

        ChoreDTO result = choreMapper.map(from);

        assertThat(result)
                .hasDatabaseId(2L)
                .hasName("chore1")
                .wasLastChangedAt(12345)
                .isDue(12345 + (1000*60*60*24*7*2));
    }

    @Test
    public void testMap_toChore() throws Exception {
        choreMapper = new ChoreDTOMapper();

        ChoreDTO from = new ChoreDTO(2L, "chore1", 12345, -1, 0);
        Chore result = choreMapper.map(from);

        assertThat(result).hasName("chore1").wasLastChangedAt(12345);
    }

}
