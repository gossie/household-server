package household.shoppinglist;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListTOAssert extends AbstractAssert<ShoppingListTOAssert, ShoppingListDTO> {

	private ShoppingListTOAssert(ShoppingListDTO actual) {
		super(actual, ShoppingListTOAssert.class);
	}
	
	public static ShoppingListTOAssert assertThat(ShoppingListDTO actual) {
		return new ShoppingListTOAssert(actual);
	}
	
	public ShoppingListTOAssert hasSize(int amount) {
		Assertions.assertThat(actual.getShoppingListGroups()).hasSize(amount);
		return this;
	}
	
	public ShoppingListTOAssert shoppingListGroup(int index, Consumer<ShoppingListGroupDTOAssert> consumer) {
		consumer.accept(ShoppingListGroupDTOAssert.assertThat(actual.getShoppingListGroups().get(index)));
		return this;
	}
}
