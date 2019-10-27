package household.cleaningplan;

import java.util.List;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class CleaningPlanDTO extends AbstractDTO {

	private final Long databaseId;
	private final List<ChoreDTO> chores;
}
