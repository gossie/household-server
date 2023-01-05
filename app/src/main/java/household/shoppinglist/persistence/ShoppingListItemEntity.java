package household.shoppinglist.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class ShoppingListItemEntity {

	private String id;
	private String name;
	private boolean selected;
	private byte[] image;
}
