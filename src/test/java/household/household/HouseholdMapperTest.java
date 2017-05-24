package household.household;

import static household.household.HouseholdDTOAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class HouseholdMapperTest {
	
	private HouseholdMapper householdMapper;

	@Test
	public void testMap_toHouseholdDTO() throws Exception {
		householdMapper = new HouseholdMapper();
		
		Household household = spy(new Household());
		when(household.getId()).thenReturn(2L);
		
		HouseholdDTO result = householdMapper.map(household);
		
		assertThat(result).isNotNull().hasDatabaseId(2L);
	}

}
