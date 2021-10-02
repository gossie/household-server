package household.cleaningplan;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class CleaningPlanMapper {

	private final ChoreMapper choreMapper;
    private final TaskMapper taskMapper;

	CleaningPlan map(CleaningPlanEntity from) {
		List<Chore> chores = from.getChores().stream()
            .map(choreMapper::map)
            .collect(Collectors.toList());

        List<Task> tasks = from.getTasks().stream()
            .map(taskMapper::map)
            .collect(Collectors.toList());

		return new CleaningPlan(from.getId(), chores, tasks);
	}

	CleaningPlanEntity map(CleaningPlan from) {
		List<ChoreEntity> chores = from.getChores().stream()
            .map(choreMapper::map)
            .collect(Collectors.toList());

        List<TaskEntity> tasks = from.getTasks().stream()
            .map(taskMapper::map)
            .collect(Collectors.toList());

		return new CleaningPlanEntity(from.getId(), chores, tasks);
	}
}
