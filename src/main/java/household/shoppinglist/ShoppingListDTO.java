package household.shoppinglist;

import java.util.List;

import household.AbstractDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingListDTO extends AbstractDTO {

	private final Long databaseId;
	private final List<ShoppingListItemDTO> shoppingListItems;
}
