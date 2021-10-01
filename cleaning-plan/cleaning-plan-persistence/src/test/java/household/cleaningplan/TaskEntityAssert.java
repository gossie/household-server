package household.cleaningplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TaskEntityAssert extends AbstractAssert<TaskEntityAssert, TaskEntity> {

	private TaskEntityAssert(TaskEntity actual) {
		super(actual, TaskEntityAssert.class);
	}

	public static TaskEntityAssert assertThat(TaskEntity actual) {
		return new TaskEntityAssert(actual);
	}

	public TaskEntityAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

}
