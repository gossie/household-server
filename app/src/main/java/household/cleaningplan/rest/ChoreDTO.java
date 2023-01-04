package household.cleaningplan.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoreDTO extends AbstractDTO {

	private String databaseId;
	private String name;
	private long lastPerformed;
	private long nextTime;
	private int repeat;

}
