package household.cleaningplan.persistence;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ChoreEntityAssert extends AbstractAssert<ChoreEntityAssert, ChoreEntity> {

	private ChoreEntityAssert(ChoreEntity actual) {
		super(actual, ChoreEntityAssert.class);
	}

	public static ChoreEntityAssert assertThat(ChoreEntity actual) {
		return new ChoreEntityAssert(actual);
	}

	public ChoreEntityAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ChoreEntityAssert wasLastChangedAt(long lastPerformed) {
		Assertions.assertThat(actual.getLastPerformed()).isEqualTo(lastPerformed);
		return this;
	}
}
