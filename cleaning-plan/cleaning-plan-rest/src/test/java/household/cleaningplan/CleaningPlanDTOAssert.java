package household.cleaningplan;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

public class CleaningPlanDTOAssert extends AbstractAssert<CleaningPlanDTOAssert, CleaningPlanDTO> {

	private CleaningPlanDTOAssert(CleaningPlanDTO actual) {
		super(actual, CleaningPlanDTOAssert.class);
	}

	public static CleaningPlanDTOAssert assertThat(CleaningPlanDTO actual) {
		return new CleaningPlanDTOAssert(actual);
	}

	public CleaningPlanDTOAssert hasDatabaseId(long id) {
		Assertions.assertThat(actual.getDatabaseId()).isEqualTo(id);
		return this;
	}

	public CleaningPlanDTOAssert hasSize(int size) {
		Assert.assertEquals(size, actual.getChores().size());
		return this;
	}
	
	public CleaningPlanDTOAssert chore(int index, Consumer<ChoreTOAssert> consumer) {
		consumer.accept(ChoreTOAssert.assertThat(actual.getChores().get(index)));
		return this;
	}
}
