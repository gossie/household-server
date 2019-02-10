package household.cleaningplan;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class DefaultCleaningPlanRepositoryTest {

    @Test
    public void deleteCleaningPlan() {
        CleaningPlanEntityRepository cleaningPlanEntityRepository = mock(CleaningPlanEntityRepository.class);

        CleaningPlanRepository cleaningPlanRepository = new DefaultCleaningPlanRepository(cleaningPlanEntityRepository, null);
        cleaningPlanRepository.deleteCleaningPlan(6L);

        verify(cleaningPlanEntityRepository).delete(6L);
    }
}
