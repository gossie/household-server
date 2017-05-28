package household.cleaningplan;

import org.assertj.core.api.AbstractAssert;
import org.junit.Assert;

public class ChoreAssert extends AbstractAssert<ChoreAssert, Chore> {

	private ChoreAssert(Chore actual) {
		super(actual, ChoreAssert.class);
	}

	public static ChoreAssert assertThat(Chore actual) {
		return new ChoreAssert(actual);
	}
	
	public ChoreAssert hasId(Long id) {
		Assert.assertEquals(id, actual.getId());
		return this;
	}
	
	public ChoreAssert hasName(String name) {
		Assert.assertEquals(name, actual.getName());
		return this;
	}
	
	public ChoreAssert wasLastChangedAt(long lastPerformed) {
		Assert.assertEquals(lastPerformed, actual.getLastPerformed());
		return this;
	}

	public ChoreAssert isDue(long date) {
		Assert.assertEquals(date, actual.determineNextTime());
		return this;
	}
}
