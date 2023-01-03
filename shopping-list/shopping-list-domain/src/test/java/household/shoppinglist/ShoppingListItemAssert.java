package household.shoppinglist;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListItemAssert extends AbstractAssert<ShoppingListItemAssert, ShoppingListItem> {

	private ShoppingListItemAssert(ShoppingListItem actual) {
		super(actual, ShoppingListItemAssert.class);
	}

	public static ShoppingListItemAssert assertThat(ShoppingListItem actual) {
		return new ShoppingListItemAssert(actual);
	}

    public ShoppingListItemAssert hasId(String id) {
        Assertions.assertThat(actual.getId()).isEqualTo(id);
        return this;
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

	public ShoppingListItemAssert hasImage(byte[] image) {
        Assertions.assertThat(actual.getImage()).isEqualTo(image);
        return this;
    }

    public ShoppingListItemAssert hasNoImage() {
        Assertions.assertThat(actual.getImage()).isNull();
        return this;
    }
}
