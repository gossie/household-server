package household.cleaningplan.persistence;

import household.cleaningplan.domain.CleaningPlan;
import household.cleaningplan.domain.CleaningPlanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultCleaningPlanRepository implements CleaningPlanRepository {

	private final CleaningPlanEntityRepository cleaningPlanEntityRepository;
	private final CleaningPlanMapper cleaningPlanMapper;

	@Override
	public CleaningPlan determineCleaningPlan(String cleaningPlanId) {
		return cleaningPlanMapper.map(cleaningPlanEntityRepository.findById(cleaningPlanId).orElseThrow(IllegalStateException::new));
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
    public void deleteCleaningPlan(String id) {
	    cleaningPlanEntityRepository.deleteById(id);
    }

}
