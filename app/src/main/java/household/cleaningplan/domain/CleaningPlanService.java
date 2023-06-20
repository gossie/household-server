package household.cleaningplan.domain;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CleaningPlanService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CleaningPlanService.class);

	private final CleaningPlanRepository cleaningPlanRepository;

	public CleaningPlan getCleaningPlan(String cleaningPlanId) {
		LOGGER.info("Retrieving cleaning plan {}", cleaningPlanId);
		return cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
	}

	public CleaningPlan addChore(String cleaningPlanId, Chore chore) {
		LOGGER.info("Adding chore to cleaning plan {}", cleaningPlanId);
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.addChore(chore);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

    public CleaningPlan update(String cleaningPlanId, Chore chore) {
		LOGGER.info("Updating chore {} to cleaning plan {}", chore.getId(), cleaningPlanId);
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.update(chore);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

    public CleaningPlan update(String cleaningPlanId, Task task) {
		LOGGER.info("Updating task {} to cleaning plan {}", task.getId(), cleaningPlanId);
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.update(task);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

	public CleaningPlan removeChore(String cleaningPlanId, String choreId) {
		LOGGER.info("Removing chore {} to cleaning plan {}", choreId, cleaningPlanId);
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.removeChore(choreId);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

    public CleaningPlan addTask(String cleaningPlanId, Task task) {
		LOGGER.info("Adding task to cleaning plan {}", cleaningPlanId);
        CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
        cleaningPlan.addTask(task);
        return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
    }

	public CleaningPlan createCleaningPlan() {
		LOGGER.info("Creating new cleaning plan");
		return cleaningPlanRepository.createCleaningPlan();
	}

	public void deleteCleaningPlan(String cleaningPlanId) {
		LOGGER.info("Deleting cleaning plan {}", cleaningPlanId);
        cleaningPlanRepository.deleteCleaningPlan(cleaningPlanId);
    }

}
