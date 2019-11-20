package household.cleaningplan;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = CleaningPlanPersistenceContext.class)
@EnableAutoConfiguration
@DirtiesContext
public class DefaultCleaningPlanRepositoryTest {

    @Autowired
    private CleaningPlanRepository cleaningPlanRepository;

    @Autowired
    private CleaningPlanEntityRepository cleaningPlanEntityRepository;

    @Test
    public void testCreateCleaningPlan() {
        Preconditions.condition(cleaningPlanEntityRepository.count() == 0L, "database not empty");

        CleaningPlan cleaningPlan = cleaningPlanRepository.createCleaningPlan();
        assertNotNull(cleaningPlan);
        assertEquals(cleaningPlan, cleaningPlanRepository.determineCleaningPlan(cleaningPlan.getId()));
    }

    @Test
    public void testDetermineCleaningPlan() {
        Preconditions.condition(cleaningPlanEntityRepository.count() == 0L, "database not empty");

        CleaningPlan cleaningPlan = cleaningPlanRepository.createCleaningPlan();
        assertNotNull(cleaningPlanRepository.determineCleaningPlan(cleaningPlan.getId()));
    }

    @Test
    public void testDeleteCleaningPlan() {
        Preconditions.condition(cleaningPlanEntityRepository.count() == 0L, "database not empty");

        CleaningPlan cleaningPlan = cleaningPlanRepository.createCleaningPlan();
        cleaningPlanRepository.deleteCleaningPlan(cleaningPlan.getId());
        assertThrows(IllegalStateException.class, () -> cleaningPlanRepository.determineCleaningPlan(cleaningPlan.getId()));
    }
}
