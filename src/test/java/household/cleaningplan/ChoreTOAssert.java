package household.cleaningplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

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
		Assert.assertEquals(name, actual.getName());
		return this;
	}
	
	public ChoreTOAssert wasLastChangedAt(long lastPerformed) {
		Assert.assertEquals(lastPerformed, actual.getLastPerformed());
		return this;
	}

	public ChoreTOAssert isDue(long nextTime) {
		Assertions.assertThat(actual.getNextTime()).isEqualTo(nextTime);
		return this;
	}
}
