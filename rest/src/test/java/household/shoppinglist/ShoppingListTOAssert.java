package household.shoppinglist;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;

public class ShoppingListTOAssert extends AbstractAssert<ShoppingListTOAssert, ShoppingListDTO> {

	private ShoppingListTOAssert(ShoppingListDTO actual) {
		super(actual, ShoppingListTOAssert.class);
	}
	
	public static ShoppingListTOAssert assertThat(ShoppingListDTO actual) {
		return new ShoppingListTOAssert(actual);
	}
	
	public ShoppingListTOAssert hasSize(int amount) {
		assertEquals(amount, actual.getShoppingListItems().size());
		return this;
	}
	
	public ShoppingListTOAssert shoppingListItem(int index, Consumer<ShoppingListItemTOAssert> consumer) {
		consumer.accept(ShoppingListItemTOAssert.assertThat(actual.getShoppingListItems().get(index)));
		return this;
	}
}
