package household.foodplan;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
public class Recipe extends AbstractModel {

    private Long cookbookId;

    Recipe(Long id, Long cookbookId) {
        super(id);
        this.cookbookId = cookbookId;
    }

}
