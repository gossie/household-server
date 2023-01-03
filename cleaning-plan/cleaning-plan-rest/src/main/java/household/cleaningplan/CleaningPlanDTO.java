package household.cleaningplan;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CleaningPlanDTO extends AbstractDTO {

	private String databaseId;
	private List<ChoreDTO> chores;
    private List<TaskDTO> tasks;
}
