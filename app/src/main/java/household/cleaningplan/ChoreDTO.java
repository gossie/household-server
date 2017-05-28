package household.cleaningplan;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ChoreDTO extends AbstractDTO {
	
	private final Long databaseId;
	private final String name;
	private final long lastPerformed;
	private final long nextTime;
	
}
