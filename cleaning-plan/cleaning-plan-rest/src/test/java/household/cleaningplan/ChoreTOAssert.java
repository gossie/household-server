package household.cleaningplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ChoreTOAssert extends AbstractAssert<ChoreTOAssert, ChoreDTO> {

	private ChoreTOAssert(ChoreDTO actual) {
		super(actual, ChoreTOAssert.class);
	}

	public static ChoreTOAssert assertThat(ChoreDTO actual) {
		return new ChoreTOAssert(actual);
	}

	public ChoreTOAssert hasDatabaseId(long id) {
		Assertions.assertThat(actual.getDatabaseId()).isEqualTo(id);
		return this;
	}

	public ChoreTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ChoreTOAssert wasLastChangedAt(long lastPerformed) {
		Assertions.assertThat(actual.getLastPerformed()).isEqualTo(lastPerformed);
		return this;
	}

	public ChoreTOAssert isDue(long nextTime) {
		Assertions.assertThat(actual.getNextTime()).isEqualTo(nextTime);
		return this;
	}
}
