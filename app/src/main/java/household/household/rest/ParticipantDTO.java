package household.household.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParticipantDTO extends AbstractDTO {

    private String databaseId;
	private String email;

}
