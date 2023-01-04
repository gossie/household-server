package household.cleaningplan.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CleaningPlanService {

	private final CleaningPlanRepository cleaningPlanRepository;

	public CleaningPlan getCleaningPlan(String cleaningPlanId) {
		return cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
	}

	public CleaningPlan addChore(String cleaningPlanId, Chore chore) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.addChore(chore);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

    public CleaningPlan update(String cleaningPlanId, Chore chore) {
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.update(chore);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

    public CleaningPlan update(String cleaningPlanId, Task task) {
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.update(task);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

	public CleaningPlan removeChore(String cleaningPlanId, String choreId) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.removeChore(choreId);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

    public CleaningPlan addTask(String cleaningPlanId, Task task) {
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.addTask(task);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

	public CleaningPlan createCleaningPlan() {
		return cleaningPlanRepository.createCleaningPlan();
	}

	public void deleteCleaningPlan(String cleaningPlanId) {
        cleaningPlanRepository.deleteCleaningPlan(cleaningPlanId);
    }

}
