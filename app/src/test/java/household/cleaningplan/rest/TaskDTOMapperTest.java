package household.cleaningplan.rest;

import org.junit.jupiter.api.Test;

import household.cleaningplan.domain.Task;

import static household.cleaningplan.domain.TaskAssert.assertThat;
import static household.cleaningplan.rest.TaskDTOAssert.assertThat;

public class TaskDTOMapperTest {

    private TaskDTOMapper taskMapper;

    @Test
    public void testMap_toTaskTO() {
        taskMapper = new TaskDTOMapper();

        Task from = new Task("2L", "task", false);

        TaskDTO result = taskMapper.map(from);

        assertThat(result).hasDatabaseId("2L").hasName("task").isNotDone();
    }

    @Test
    public void testMap_toTask() {
        taskMapper = new TaskDTOMapper();

        TaskDTO from = new TaskDTO("2L", "task", true);
        Task result = taskMapper.map(from);

        assertThat(result).hasName("task").isDone();
    }

}
