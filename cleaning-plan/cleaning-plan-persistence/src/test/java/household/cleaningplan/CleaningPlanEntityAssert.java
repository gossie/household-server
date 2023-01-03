package household.cleaningplan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;

public class CleaningPlanEntityAssert extends AbstractAssert<CleaningPlanEntityAssert, CleaningPlanEntity> {

	private CleaningPlanEntityAssert(CleaningPlanEntity actual) {
		super(actual, CleaningPlanEntityAssert.class);
	}

	public static final CleaningPlanEntityAssert assertThat(CleaningPlanEntity actual) {
		return new CleaningPlanEntityAssert(actual);
	}

	public CleaningPlanEntityAssert hasId(String id) {
		assertEquals(id, actual.getId());
		return this;
	}

	public CleaningPlanEntityAssert hasSize(int amount) {
		assertEquals(amount, actual.getChores().size());
		return this;
	}

	public CleaningPlanEntityAssert chore(int index, Consumer<ChoreEntityAssert> consumer) {
		consumer.accept(ChoreEntityAssert.assertThat(actual.getChores().get(index)));
		return this;
	}
}
