package household.cleaningplan;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultCleaningPlanRepository implements CleaningPlanRepository {

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

	@Override
	public CleaningPlan createCleaningPlan() {
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.save(new CleaningPlanEntity()));
	}

	@Override
    public void deleteCleaningPlan(Long id) {
	    cleaningPlanEntityRepository.delete(id);
    }

}
