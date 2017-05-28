package household.household;

import static household.household.HouseholdAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class HouseholdMapperTest {
	
	private HouseholdMapper householdMapper;

	@Test
	public void testMap_toHouseholdDTO() throws Exception {
		householdMapper = new HouseholdMapper();
		
		HouseholdEntity household = spy(new HouseholdEntity());
		when(household.getId()).thenReturn(2L);
		
		Household result = householdMapper.map(household);
		
		assertThat(result).isNotNull().hasId(2L);
	}

}
