package household.foodplan.domain;

public interface FoodPlanRepository {

	FoodPlan determineFoodPlan(String foodPlanId);

	FoodPlan saveFoodPlan(FoodPlan foodPlan);

	FoodPlan createFoodPlan();

    void deleteFoodPlan(String foodPlanId);
}