package household.household.rest;

import static household.household.rest.HouseholdDTOAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import household.household.domain.Household;

public class HouseholdDTOMapperTest {

	private HouseholdDTOMapper householdMapper;

	@Test
	public void testMap_toHouseholdDTO() throws Exception {
		householdMapper = new HouseholdDTOMapper();

		Household household = spy(new Household("1L", null, null, null, null));
		when(household.getId()).thenReturn("2L");

		HouseholdDTO result = householdMapper.map(household);

		assertThat(result).isNotNull().hasDatabaseId("2L");
	}

}
