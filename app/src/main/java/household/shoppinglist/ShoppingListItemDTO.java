package household.shoppinglist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingListItemDTO {

	private final String name;
	private final boolean selected;
}
