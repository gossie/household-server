package household.cleaningplan;

import org.junit.jupiter.api.Test;

import static household.cleaningplan.TaskAssert.assertThat;
import static household.cleaningplan.TaskEntityAssert.assertThat;

public class TaskMapperTest {

    private TaskMapper taskMapper;

    @Test
    public void testMap_toTask() {
        taskMapper = new TaskMapper();

        TaskEntity from = new TaskEntity(2L, "task", true);

        Task result = taskMapper.map(from);

        assertThat(result)
            .hasId(2L)
            .hasName("task")
            .isDone();
    }

    @Test
    public void testMap_toTaskEntity() {
        taskMapper = new TaskMapper();

        Task from = new Task(2L, "task", false);
        TaskEntity result = taskMapper.map(from);

        assertThat(result).hasName("task");
    }

}
