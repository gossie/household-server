package household.cleaningplan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CleaningPlanMapper {
	
	private final ChoreMapper choreMapper;

	public CleaningPlan map(CleaningPlanEntity from) {
		List<Chore> chores = from.getChores().stream().map(choreMapper::map).collect(Collectors.toList());
		return new CleaningPlan(from.getId(), chores);
	}

	public CleaningPlanEntity map(CleaningPlan from) {
		List<ChoreEntity> chores = from.getChores().stream().map(choreMapper::map).collect(Collectors.toList());
		return new CleaningPlanEntity();
	}
}
