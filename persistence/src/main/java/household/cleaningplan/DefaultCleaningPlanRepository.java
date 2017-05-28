package household.cleaningplan;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class DefaultCleaningPlanRepository implements CleaningPlanRepository {
	
	private final CleaningPlanEntityRepository cleaningPlanEntityRepository;
	private final CleaningPlanMapper cleaningPlanMapper;

	@Override
	public CleaningPlan determineCleaningPlan(Long cleaningPlanId) {
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.findOne(cleaningPlanId));
	}

	@Override
	public CleaningPlan saveCleaningPlan(CleaningPlan cleaningPlan) {
		CleaningPlanEntity cleaningPlanEntity = cleaningPlanMapper.map(cleaningPlan);
		
		
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.save(cleaningPlanEntity));
	}

}
