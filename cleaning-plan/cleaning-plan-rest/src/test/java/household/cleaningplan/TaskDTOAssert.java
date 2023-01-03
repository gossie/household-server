package household.cleaningplan;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TaskDTOAssert extends AbstractAssert<TaskDTOAssert, TaskDTO> {

	private TaskDTOAssert(TaskDTO actual) {
		super(actual, TaskDTOAssert.class);
	}

	public static TaskDTOAssert assertThat(TaskDTO actual) {
		return new TaskDTOAssert(actual);
	}

	public TaskDTOAssert hasDatabaseId(String id) {
		Assertions.assertThat(actual.getDatabaseId()).isEqualTo(id);
		return this;
	}

	public TaskDTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

    public TaskDTOAssert isNotDone() {
        Assertions.assertThat(actual.isDone()).isFalse();
        return this;
    }
}
