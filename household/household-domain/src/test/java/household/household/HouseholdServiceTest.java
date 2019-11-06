package household.household;

import static household.household.HouseholdAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HouseholdServiceTest {

	private HouseholdService householdService;

	@Test
	public void testGetHousehold() throws Exception {
		Household expected = mock(Household.class);

		HouseholdRepository householdRepository = mock(HouseholdRepository.class);
		when(householdRepository.determineHousehold(7L)).thenReturn(expected);

		householdService = new HouseholdService(householdRepository);
		Household result = householdService.getHousehold(7L);

		assertThat(result).isSameAs(expected);
	}

	@Test
	@Disabled
	public void testCreateHousehold() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
