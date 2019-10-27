package household.cleaningplan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;

public class CleaningPlanAssert extends AbstractAssert<CleaningPlanAssert, CleaningPlan> {

	private CleaningPlanAssert(CleaningPlan actual) {
		super(actual, CleaningPlanAssert.class);
	}

	public static final CleaningPlanAssert assertThat(CleaningPlan actual) {
		return new CleaningPlanAssert(actual);
	}

	public CleaningPlanAssert hasId(Long id) {
		assertEquals(id, actual.getId());
		return this;
	}

	public CleaningPlanAssert hasSize(int amount) {
		assertEquals(amount, actual.getChores().size());
		return this;
	}

	public CleaningPlanAssert chore(int index, Consumer<ChoreAssert> consumer) {
		consumer.accept(ChoreAssert.assertThat(actual.getChores().get(index)));
		return this;
	}
}
