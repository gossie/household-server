package household.cleaningplan;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CleaningPlanDTOAssert extends AbstractAssert<CleaningPlanDTOAssert, CleaningPlanDTO> {

	private CleaningPlanDTOAssert(CleaningPlanDTO actual) {
		super(actual, CleaningPlanDTOAssert.class);
	}

	public static CleaningPlanDTOAssert assertThat(CleaningPlanDTO actual) {
		return new CleaningPlanDTOAssert(actual);
	}

	public CleaningPlanDTOAssert hasDatabaseId(String id) {
		Assertions.assertThat(actual.getDatabaseId()).isEqualTo(id);
		return this;
	}

	public CleaningPlanDTOAssert hasSize(int size) {
		Assertions.assertThat(actual.getChores()).hasSize(size);
		return this;
	}

	public CleaningPlanDTOAssert chore(int index, Consumer<ChoreDTOAssert> consumer) {
		consumer.accept(ChoreDTOAssert.assertThat(actual.getChores().get(index)));
		return this;
	}
}
