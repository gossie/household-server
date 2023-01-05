package household.foodplan.rest;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class MealTOAssert extends AbstractAssert<MealTOAssert, MealDTO> {

	private MealTOAssert(MealDTO actual) {
		super(actual, MealTOAssert.class);
	}

	public static MealTOAssert assertThat(MealDTO actual) {
		return new MealTOAssert(actual);
	}

	public MealTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public MealTOAssert hasDatabaseId(String id) {
		Assertions.assertThat(actual.getDatabaseId()).isSameAs(id);
		return this;
	}
}
