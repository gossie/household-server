package household.cleaningplan.persistence;

import household.cleaningplan.domain.Task;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access= AccessLevel.PACKAGE)
class TaskMapper {

    Task map(TaskEntity from) {
        return new Task(from.getId(), from.getName(), from.isDone());
    }

    TaskEntity map(Task from) {
        return new TaskEntity(from.getId(), from.getName(), from.isDone());
    }

}
