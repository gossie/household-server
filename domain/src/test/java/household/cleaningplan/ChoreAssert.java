package household.cleaningplan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.AbstractAssert;

public class ChoreAssert extends AbstractAssert<ChoreAssert, Chore> {

	private ChoreAssert(Chore actual) {
		super(actual, ChoreAssert.class);
	}

	public static ChoreAssert assertThat(Chore actual) {
		return new ChoreAssert(actual);
	}

	public ChoreAssert hasId(Long id) {
		assertEquals(id, actual.getId());
		return this;
	}

	public ChoreAssert hasName(String name) {
		assertEquals(name, actual.getName());
		return this;
	}

	public ChoreAssert wasLastChangedAt(long lastPerformed) {
		assertEquals(lastPerformed, actual.getLastPerformed());
		return this;
	}

	public ChoreAssert isDue(long date) {
		assertEquals(date, actual.determineNextTime());
		return this;
	}
}
