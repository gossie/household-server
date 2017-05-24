package household.shoppinglist;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListItemTOAssert extends AbstractAssert<ShoppingListItemTOAssert, ShoppingListItemDTO> {

	private ShoppingListItemTOAssert(ShoppingListItemDTO actual) {
		super(actual, ShoppingListItemTOAssert.class);
	}
	
	public static ShoppingListItemTOAssert assertThat(ShoppingListItemDTO actual) {
		return new ShoppingListItemTOAssert(actual);
	}
	
	public ShoppingListItemTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ShoppingListItemTOAssert isSelected() {
		Assertions.assertThat(actual.isSelected()).isTrue();
		return this;
	}
	
	public ShoppingListItemTOAssert isDeselected() {
		Assertions.assertThat(actual.isSelected()).isFalse();
		return this;
	}

}
