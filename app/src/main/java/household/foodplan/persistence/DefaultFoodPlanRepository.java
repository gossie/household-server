package household.foodplan.persistence;

import household.foodplan.domain.FoodPlan;
import household.foodplan.domain.FoodPlanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultFoodPlanRepository implements FoodPlanRepository {

	private final FoodPlanEntityRepository foodPlanEntityRepository;
	private final FoodPlanMapper foodPlanMapper;

	@Override
	public FoodPlan determineFoodPlan(String foodPlanId) {
		return foodPlanMapper.map(foodPlanEntityRepository.findById(foodPlanId).orElseThrow(IllegalStateException::new));
	}

	@Override
	public FoodPlan saveFoodPlan(FoodPlan foodPlan) {
		return foodPlanMapper.map(foodPlanEntityRepository.save(foodPlanMapper.map(foodPlan)));
	}

	@Override
	public FoodPlan createFoodPlan(FoodPlan foodPlan) {
		var foodPlanEntity = foodPlanMapper.map(foodPlan);
		foodPlanEntity.setId(null);
		return foodPlanMapper.map(foodPlanEntityRepository.save(foodPlanEntity));
	}

	@Override
    public void deleteFoodPlan(String foodPlanId) {
	    foodPlanEntityRepository.deleteById(foodPlanId);
    }

}
