package household.household.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ParticipantDTO extends AbstractDTO {

    private final String databaseId;
	private final String email;

}
