package household.foodplan;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
public class Recipe extends AbstractModel {

    private String cookbookId;

    Recipe(String id, String cookbookId) {
        super(id);
        this.cookbookId = cookbookId;
    }

}
