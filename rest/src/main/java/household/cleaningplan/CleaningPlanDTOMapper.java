package household.cleaningplan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CleaningPlanDTOMapper {
	
	private final ChoreDTOMapper choreMapper;

	public CleaningPlanDTO map(CleaningPlan from) {
		List<ChoreDTO> chores = from.getChores().stream().map(choreMapper::map).collect(Collectors.toList());
		return new CleaningPlanDTO(from.getId(), chores);
	}
}
