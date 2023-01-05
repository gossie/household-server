package household.cleaningplan.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import household.cleaningplan.domain.CleaningPlanRepository;
import org.junit.jupiter.api.Test;

public class DefaultCleaningPlanRepositoryTest {

    @Test
    public void deleteCleaningPlan() {
        CleaningPlanEntityRepository cleaningPlanEntityRepository = mock(CleaningPlanEntityRepository.class);

        CleaningPlanRepository cleaningPlanRepository = new DefaultCleaningPlanRepository(cleaningPlanEntityRepository, null);
        cleaningPlanRepository.deleteCleaningPlan("6L");

        verify(cleaningPlanEntityRepository).deleteById("6L");
    }
}
