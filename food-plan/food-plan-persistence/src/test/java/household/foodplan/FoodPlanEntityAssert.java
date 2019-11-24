package household.foodplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class FoodPlanEntityAssert extends AbstractAssert<FoodPlanEntityAssert, FoodPlanEntity> {

	private FoodPlanEntityAssert(FoodPlanEntity actual) {
		super(actual, FoodPlanEntityAssert.class);
	}
	
	public static FoodPlanEntityAssert assertThat(FoodPlanEntity actual) {
		return new FoodPlanEntityAssert(actual);
	}
	
	public FoodPlanEntityAssert hasSize(int size) {
		Assertions.assertThat(actual.getMeals()).hasSize(size);
		return this;
	}
}

