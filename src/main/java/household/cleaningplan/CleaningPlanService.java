package household.cleaningplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CleaningPlanService {
	
	private final CleaningPlanRepository cleaningPlanRepository;

	public CleaningPlan getCleaningPlan(Long cleaningPlanId) {
		return cleaningPlanRepository.findOne(cleaningPlanId);
	}
	
	public CleaningPlan addChore(Long cleaningPlanId, Chore chore) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.findOne(cleaningPlanId);
		cleaningPlan.addChore(chore);
		return cleaningPlanRepository.save(cleaningPlan);
	}

	public CleaningPlan update(Long cleaningPlanId, Chore chore) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.findOne(cleaningPlanId);
		cleaningPlan.update(chore);
		return cleaningPlanRepository.save(cleaningPlan);
	}

	public CleaningPlan removeChore(Long cleaningPlanId, Long choreId) {
		CleaningPlan cleaningPlan = cleaningPlanRepository.findOne(cleaningPlanId);
		cleaningPlan.removeChore(choreId);
		return cleaningPlanRepository.save(cleaningPlan);
	}

}
