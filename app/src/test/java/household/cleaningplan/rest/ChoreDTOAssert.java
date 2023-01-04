package household.cleaningplan.rest;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ChoreDTOAssert extends AbstractAssert<ChoreDTOAssert, ChoreDTO> {

	private ChoreDTOAssert(ChoreDTO actual) {
		super(actual, ChoreDTOAssert.class);
	}

	public static ChoreDTOAssert assertThat(ChoreDTO actual) {
		return new ChoreDTOAssert(actual);
	}

	public ChoreDTOAssert hasDatabaseId(String id) {
		Assertions.assertThat(actual.getDatabaseId()).isEqualTo(id);
		return this;
	}

	public ChoreDTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ChoreDTOAssert wasLastChangedAt(long lastPerformed) {
		Assertions.assertThat(actual.getLastPerformed()).isEqualTo(lastPerformed);
		return this;
	}

	public ChoreDTOAssert isDue(long nextTime) {
		Assertions.assertThat(actual.getNextTime()).isEqualTo(nextTime);
		return this;
	}
}
