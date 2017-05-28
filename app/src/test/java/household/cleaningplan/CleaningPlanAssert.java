package household.cleaningplan;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;

public class CleaningPlanAssert extends AbstractAssert<CleaningPlanAssert, CleaningPlanEntity> {

	private CleaningPlanAssert(CleaningPlanEntity actual) {
		super(actual, CleaningPlanAssert.class);
	}

	public static final CleaningPlanAssert assertThat(CleaningPlanEntity actual) {
		return new CleaningPlanAssert(actual);
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
