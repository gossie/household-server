package household.cleaningplan;

import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultCleaningPlanRepository implements CleaningPlanRepository {
	
	private final CleaningPlanEntityRepository cleaningPlanEntityRepository;
	private final CleaningPlanMapper cleaningPlanMapper;

	@Override
    @Transactional
	public CleaningPlan determineCleaningPlan(Long cleaningPlanId) {
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.findOne(cleaningPlanId));
	}

	@Override
    @Transactional
	public CleaningPlan saveCleaningPlan(CleaningPlan cleaningPlan) {
		CleaningPlanEntity cleaningPlanEntity = cleaningPlanMapper.map(cleaningPlan);
		
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.save(cleaningPlanEntity));
	}

	@Override
    @Transactional
	public CleaningPlan createCleaningPlan() {
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.save(new CleaningPlanEntity()));
	}

}
