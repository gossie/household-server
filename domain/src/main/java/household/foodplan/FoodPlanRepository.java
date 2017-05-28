package household.foodplan;

public interface FoodPlanRepository {

	FoodPlan determineFoodPlan(long foodPlanId);

	FoodPlan saveFoodPlan(FoodPlan foodPlan);
}
