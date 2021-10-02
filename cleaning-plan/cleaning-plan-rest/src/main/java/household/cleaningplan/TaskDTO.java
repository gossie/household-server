package household.cleaningplan;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class TaskDTO extends AbstractDTO {

    private final Long databaseId;
    private final String name;
    private final boolean done;

}
