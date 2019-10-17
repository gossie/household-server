package household.household;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class DefaultHouseholdRepositoryTest {

    @Test
    public void deleteHousehold() {
        HouseholdEntityRepository householdEntityRepository = mock(HouseholdEntityRepository.class);

        HouseholdRepository householdRepository = new DefaultHouseholdRepository(householdEntityRepository, null);
        householdRepository.deleteHousehold(7L);

        verify(householdEntityRepository).deleteById(7L);
    }
}
