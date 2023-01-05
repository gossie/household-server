package household.cleaningplan.rest;

import java.util.List;
import java.util.stream.Collectors;

import household.cleaningplan.domain.CleaningPlan;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class CleaningPlanDTOMapper {

    private final ChoreDTOMapper choreMapper;
    private final TaskDTOMapper taskMapper;

	CleaningPlanDTO map(CleaningPlan from) {
        List<ChoreDTO> chores = from.getChores().stream()
            .map(choreMapper::map)
            .collect(Collectors.toList());

        List<TaskDTO> tasks = from.getTasks().stream()
            .map(taskMapper::map)
            .collect(Collectors.toList());

		return new CleaningPlanDTO(from.getId(), chores, tasks);
	}
}
