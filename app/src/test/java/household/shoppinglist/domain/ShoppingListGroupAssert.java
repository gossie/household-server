package household.shoppinglist.domain;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListGroupAssert extends AbstractAssert<ShoppingListGroupAssert, ShoppingListGroup> {

    private ShoppingListGroupAssert(ShoppingListGroup actual) {
        super(actual, ShoppingListGroupAssert.class);
    }

    public static ShoppingListGroupAssert assertThat(ShoppingListGroup actual) {
        return new ShoppingListGroupAssert(actual);
    }

    public ShoppingListGroupAssert hasId(String id) {
        Assertions.assertThat(actual.getId()).isEqualTo(id);
        return this;
    }

    public ShoppingListGroupAssert hasName(String name) {
        Assertions.assertThat(actual.getName()).isEqualTo(name);
        return this;
    }

    public ShoppingListGroupAssert shoppingListItem(int index, Consumer<ShoppingListItemAssert> consumer) {
        consumer.accept(ShoppingListItemAssert.assertThat(actual.getShoppingListItems().get(index)));
        return this;
    }

    public ShoppingListGroupAssert hasSize(int size) {
        Assertions.assertThat(actual.getShoppingListItems()).hasSize(size);
        return this;
    }
}
