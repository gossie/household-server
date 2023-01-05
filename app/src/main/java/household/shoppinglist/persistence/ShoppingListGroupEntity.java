package household.shoppinglist.persistence;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListGroupEntity {

    private String id;
    private String name;

    private List<ShoppingListItemEntity> shoppingListItems = new ArrayList<>();
}
