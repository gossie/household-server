package household.household;

import static household.household.HouseholdDTOAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class HouseholdDTOMapperTest {

	private HouseholdDTOMapper householdMapper;

	@Test
	public void testMap_toHouseholdDTO() throws Exception {
		householdMapper = new HouseholdDTOMapper();

		Household household = new Household(2L, null, null, null, null);

		HouseholdDTO result = householdMapper.map(household);

		assertThat(result).isNotNull().hasDatabaseId(2L);
	}

}
