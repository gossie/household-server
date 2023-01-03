package household.cleaningplan;

public interface CleaningPlanRepository {

	CleaningPlan determineCleaningPlan(String cleaningPlanId);

	CleaningPlan saveCleaningPlan(CleaningPlan cleaningPlan);

	CleaningPlan createCleaningPlan();

    void deleteCleaningPlan(String cleaningPlanId);
}
