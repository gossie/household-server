package household.user;

import household.AbstractDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class UserDTO extends AbstractDTO {

	private final Long databaseId;

}
