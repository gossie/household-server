package household.cleaningplan.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends AbstractDTO {

    private String databaseId;
    private String name;
    private boolean done;

}
