package household.household;

import org.assertj.core.api.AbstractAssert;

public class HouseholdAssert extends AbstractAssert<HouseholdAssert, Household> {

	private HouseholdAssert(Household actual) {
		super(actual, HouseholdAssert.class);
	}

	public static HouseholdAssert assertThat(Household actual) {
		return new HouseholdAssert(actual);
	}
}
