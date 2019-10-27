package household.v1.shoppinglist;

import java.util.List;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingListDTOV1 extends AbstractDTO {

	private final Long databaseId;
	private final List<ShoppingListItemDTOV1> shoppingListItems;
}
