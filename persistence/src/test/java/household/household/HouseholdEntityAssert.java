package household.household;

import org.assertj.core.api.AbstractAssert;

public class HouseholdEntityAssert extends AbstractAssert<HouseholdEntityAssert, HouseholdEntity> {

	private HouseholdEntityAssert(HouseholdEntity actual) {
		super(actual, HouseholdEntityAssert.class);
	}

	public static HouseholdEntityAssert assertThat(HouseholdEntity actual) {
		return new HouseholdEntityAssert(actual);
	}
}
