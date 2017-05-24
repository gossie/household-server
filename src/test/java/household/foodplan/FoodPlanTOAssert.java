package household.foodplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class FoodPlanTOAssert extends AbstractAssert<FoodPlanTOAssert, FoodPlanDTO> {
	private FoodPlanTOAssert(FoodPlanDTO actual) {
		super(actual, FoodPlanTOAssert.class);
	}
	
	public static FoodPlanTOAssert assertThat(FoodPlanDTO actual) {
		return new FoodPlanTOAssert(actual);
	}
	
	public FoodPlanTOAssert hasSize(int size) {
		Assertions.assertThat(actual.getMeals()).hasSize(size);
		return this;
	}

	public FoodPlanTOAssert hasDatabaseId(long id) {
		Assertions.assertThat(actual.getDatabaseId()).isSameAs(id);
		return this;
	}
}
