package household.shoppinglist;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListGroupDTO extends AbstractDTO {

    private String databaseId;
    private String name;
    private List<ShoppingListItemDTO> shoppingListItems;



}
