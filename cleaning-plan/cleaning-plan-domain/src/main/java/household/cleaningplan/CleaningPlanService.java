package household.cleaningplan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CleaningPlanService {

	private final CleaningPlanRepository cleaningPlanRepository;

	public CleaningPlan getCleaningPlan(Long cleaningPlanId) {
		return cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
	}

	public CleaningPlan addChore(Long cleaningPlanId, Chore chore) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.addChore(chore);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

    public CleaningPlan update(Long cleaningPlanId, Chore chore) {
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.update(chore);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

    public CleaningPlan update(Long cleaningPlanId, Task task) {
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.update(task);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

	public CleaningPlan removeChore(Long cleaningPlanId, Long choreId) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.removeChore(choreId);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

    public CleaningPlan addTask(long cleaningPlanId, Task task) {
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.addTask(task);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

	public CleaningPlan createCleaningPlan() {
		return cleaningPlanRepository.createCleaningPlan();
	}

	public void deleteCleaningPlan(Long cleaningPlanId) {
        cleaningPlanRepository.deleteCleaningPlan(cleaningPlanId);
    }

}
