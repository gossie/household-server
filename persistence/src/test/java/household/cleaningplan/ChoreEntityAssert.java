package household.cleaningplan;

import org.assertj.core.api.AbstractAssert;
import org.junit.Assert;

public class ChoreEntityAssert extends AbstractAssert<ChoreEntityAssert, ChoreEntity> {

	private ChoreEntityAssert(ChoreEntity actual) {
		super(actual, ChoreEntityAssert.class);
	}

	public static ChoreEntityAssert assertThat(ChoreEntity actual) {
		return new ChoreEntityAssert(actual);
	}
	
	public ChoreEntityAssert hasName(String name) {
		Assert.assertEquals(name, actual.getName());
		return this;
	}
	
	public ChoreEntityAssert wasLastChangedAt(long lastPerformed) {
		Assert.assertEquals(lastPerformed, actual.getLastPerformed());
		return this;
	}
}
