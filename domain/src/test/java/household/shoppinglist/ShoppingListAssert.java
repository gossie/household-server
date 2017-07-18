package household.shoppinglist;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;

public class ShoppingListAssert extends AbstractAssert<ShoppingListAssert, ShoppingList> {

	private ShoppingListAssert(ShoppingList actual) {
		super(actual, ShoppingListAssert.class);
	}
	
	public static ShoppingListAssert assertThat(ShoppingList actual) {
		return new ShoppingListAssert(actual);
	}
	
	public ShoppingListAssert hasSize(int amount) {
		assertEquals(amount, actual.getShoppingListGroups().size());
		return this;
	}
	
	public ShoppingListAssert shoppingListGroup(int index, Consumer<ShoppingListGroupAssert> consumer) {
		consumer.accept(ShoppingListGroupAssert.assertThat(actual.getShoppingListGroups().get(index)));
		return this;
	}
}
