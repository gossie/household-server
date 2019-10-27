package household.shoppinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.AbstractAssert;

public class ShoppingListEntityAssert extends AbstractAssert<ShoppingListEntityAssert, ShoppingListEntity> {

	private ShoppingListEntityAssert(ShoppingListEntity actual) {
		super(actual, ShoppingListEntityAssert.class);
	}

	public static ShoppingListEntityAssert assertThat(ShoppingListEntity actual) {
		return new ShoppingListEntityAssert(actual);
	}

	public ShoppingListEntityAssert hasSize(int amount) {
		assertEquals(amount, actual.getShoppingListGroups().size());
		return this;
	}
}
