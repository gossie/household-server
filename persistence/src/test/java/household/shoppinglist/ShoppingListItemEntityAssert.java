package household.shoppinglist;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListItemEntityAssert extends AbstractAssert<ShoppingListItemEntityAssert, ShoppingListItemEntity> {

	private ShoppingListItemEntityAssert(ShoppingListItemEntity actual) {
		super(actual, ShoppingListItemEntityAssert.class);
	}

	public static ShoppingListItemEntityAssert assertThat(ShoppingListItemEntity actual) {
		return new ShoppingListItemEntityAssert(actual);
	}
	
	public ShoppingListItemEntityAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ShoppingListItemEntityAssert isSelected() {
		Assertions.assertThat(actual.isSelected()).isTrue();
		return this;
	}

	public ShoppingListItemEntityAssert isDeselected() {
		Assertions.assertThat(actual.isSelected()).isFalse();
		return this;
	}
}
