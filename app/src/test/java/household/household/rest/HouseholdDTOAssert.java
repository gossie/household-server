package household.household.rest;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class HouseholdDTOAssert extends AbstractAssert<HouseholdDTOAssert, HouseholdDTO> {

	private HouseholdDTOAssert(HouseholdDTO actual) {
		super(actual, HouseholdDTOAssert.class);
	}

	public static HouseholdDTOAssert assertThat(HouseholdDTO actual) {
		return new HouseholdDTOAssert(actual);
	}

	public HouseholdDTOAssert hasDatabaseId(String id) {
		Assertions.assertThat(actual.getDatabaseId()).isSameAs(id);
		return this;
	}
}
