package household.cleaningplan.rest;

import household.cleaningplan.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TaskDTOMapper {

    TaskDTO map(Task from) {
        return new TaskDTO(from.getId(), from.getName(), from.isDone());
    }

    Task map(TaskDTO from) {
        return new Task(from.getDatabaseId(), from.getName(), from.isDone());
    }

    Task map(String choreId, TaskDTO from) {
        return new Task(choreId, from.getName(), from.isDone());
    }

}
