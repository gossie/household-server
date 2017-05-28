package household.shoppinglist;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListItemAssert extends AbstractAssert<ShoppingListItemAssert, ShoppingListItemEntity> {

	private ShoppingListItemAssert(ShoppingListItemEntity actual) {
		super(actual, ShoppingListItemAssert.class);
	}

	public static ShoppingListItemAssert assertThat(ShoppingListItemEntity actual) {
		return new ShoppingListItemAssert(actual);
	}
	
	public ShoppingListItemAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ShoppingListItemAssert isSelected() {
		Assertions.assertThat(actual.isSelected()).isTrue();
		return this;
	}

	public ShoppingListItemAssert isDeselected() {
		Assertions.assertThat(actual.isSelected()).isFalse();
		return this;
	}
}
