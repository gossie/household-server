package household.cleaningplan.domain;

import org.assertj.core.api.AbstractAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskAssert extends AbstractAssert<TaskAssert, Task> {

    private TaskAssert(Task actual) {
        super(actual, TaskAssert.class);
    }

    public static TaskAssert assertThat(Task actual) {
        return new TaskAssert(actual);
    }

    public TaskAssert hasId(String id) {
        assertEquals(id, actual.getId());
        return this;
    }

    public TaskAssert hasName(String name) {
        assertEquals(name, actual.getName());
        return this;
    }

    public TaskAssert isDone() {
        assertTrue(actual.isDone());
        return this;
    }

    public TaskAssert isNotDone() {
        assertFalse(actual.isDone());
        return this;
    }
}
