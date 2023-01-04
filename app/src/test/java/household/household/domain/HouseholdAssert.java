package household.household.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class HouseholdAssert extends AbstractAssert<HouseholdAssert, Household> {

	private HouseholdAssert(Household actual) {
		super(actual, HouseholdAssert.class);
	}

	public static HouseholdAssert assertThat(Household actual) {
		return new HouseholdAssert(actual);
	}

	public HouseholdAssert hasId(String id) {
		Assertions.assertThat(actual.getId()).isEqualTo(id);
		return this;
	}
}
