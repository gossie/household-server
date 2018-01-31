package household.foodplan;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultFoodPlanRepository implements FoodPlanRepository {

	private final FoodPlanEntityRepository foodPlanEntityRepository;
	private final FoodPlanMapper foodPlanMapper;
	
	@Override
	public FoodPlan determineFoodPlan(long foodPlanId) {
		return foodPlanMapper.map(foodPlanEntityRepository.findOne(foodPlanId));
	}

	@Override
	public FoodPlan saveFoodPlan(FoodPlan foodPlan) {
		return foodPlanMapper.map(foodPlanEntityRepository.save(foodPlanMapper.map(foodPlan)));
	}

	@Override
	public FoodPlan createFoodPlan() {
		return foodPlanMapper.map(foodPlanEntityRepository.save(new FoodPlanEntity()));
	}

}
