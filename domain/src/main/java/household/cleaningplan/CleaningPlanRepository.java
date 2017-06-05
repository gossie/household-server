package household.cleaningplan;

public interface CleaningPlanRepository {

	CleaningPlan determineCleaningPlan(Long cleaningPlanId);

	CleaningPlan saveCleaningPlan(CleaningPlan cleaningPlan);

	CleaningPlan createCleaningPlan();

}
