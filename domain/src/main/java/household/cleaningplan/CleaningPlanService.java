package household.cleaningplan;

import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

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

	public CleaningPlan removeChore(Long cleaningPlanId, Long choreId) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.determineCleaningPlan(cleaningPlanId);
		cleaningPlan.removeChore(choreId);
		return cleaningPlanRepository.saveCleaningPlan(cleaningPlan);
	}

	public CleaningPlan createCleaningPlan() {
		return cleaningPlanRepository.createCleaningPlan();
	}

	private void deleteCleaningPlan(Long cleaningPlanId) {
        cleaningPlanRepository.deleteCleaningPlan(cleaningPlanId);
    }

	@Subscribe
	public void onHouseholdDeleted(HouseholdDeletedEvent event) {
	    deleteCleaningPlan(event.getHousehold().getCleaningPlanId());
    }
}
