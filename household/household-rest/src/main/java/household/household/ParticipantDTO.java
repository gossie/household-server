package household.household;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ParticipantDTO extends AbstractDTO {

    private final Long databaseId;
	private final String email;

}
