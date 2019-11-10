package household.shoppinglist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingListItemDTO extends AbstractDTO {

    private final Long databaseId;
	private final String name;
	private final boolean selected;

}
