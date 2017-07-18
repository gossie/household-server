package household.v1.shoppinglist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingListItemDTOV1 {

	private final String name;
	private final boolean selected;
}
