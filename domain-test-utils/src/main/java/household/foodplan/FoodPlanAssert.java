package household.foodplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class FoodPlanAssert extends AbstractAssert<FoodPlanAssert, FoodPlan> {

	private FoodPlanAssert(FoodPlan actual) {
		super(actual, FoodPlanAssert.class);
	}
	
	public static FoodPlanAssert assertThat(FoodPlan actual) {
		return new FoodPlanAssert(actual);
	}

	public FoodPlanAssert hasId(Long id) {
		Assertions.assertThat(actual.getId()).isEqualTo(id);
		return this;
	}
	
	public FoodPlanAssert hasSize(int size) {
		Assertions.assertThat(actual.getMeals()).hasSize(size);
		return this;
	}
}

