package household.shoppinglist;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingListDTO extends AbstractDTO {

	private final String databaseId;
	private final List<ShoppingListGroupDTO> shoppingListGroups;
}
