package household.cleaningplan;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class CleaningPlanMapper {

	private final ChoreMapper choreMapper;

	CleaningPlan map(CleaningPlanEntity from) {
		List<Chore> chores = from.getChores().stream()
            .map(choreMapper::map)
            .collect(Collectors.toList());

		return new CleaningPlan(from.getId(), chores);
	}

	CleaningPlanEntity map(CleaningPlan from) {
		List<ChoreEntity> chores = from.getChores().stream()
            .map(choreMapper::map)
            .collect(Collectors.toList());

		return new CleaningPlanEntity(from.getId(), chores);
	}
}
