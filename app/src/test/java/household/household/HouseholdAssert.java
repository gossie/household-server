package household.household;

import org.assertj.core.api.AbstractAssert;

public class HouseholdAssert extends AbstractAssert<HouseholdAssert, HouseholdEntity> {

	private HouseholdAssert(HouseholdEntity actual) {
		super(actual, HouseholdAssert.class);
	}

	public static HouseholdAssert assertThat(HouseholdEntity actual) {
		return new HouseholdAssert(actual);
	}
}
