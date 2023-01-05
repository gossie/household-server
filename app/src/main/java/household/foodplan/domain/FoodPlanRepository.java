package household.foodplan.domain;

public interface FoodPlanRepository {

	FoodPlan determineFoodPlan(String foodPlanId);

	FoodPlan saveFoodPlan(FoodPlan foodPlan);

	FoodPlan createFoodPlan(FoodPlan foodPlan);

    void deleteFoodPlan(String foodPlanId);
}
