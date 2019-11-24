package household.shoppinglist;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListGroupEntityAssert extends AbstractAssert<ShoppingListGroupEntityAssert, ShoppingListGroupEntity> {

    private ShoppingListGroupEntityAssert(ShoppingListGroupEntity actual) {
        super(actual, ShoppingListGroupEntityAssert.class);
    }

    public static final ShoppingListGroupEntityAssert assertThat(ShoppingListGroupEntity actual) {
        return new ShoppingListGroupEntityAssert(actual);
    }

    public ShoppingListGroupEntityAssert hasName(String name) {
        Assertions.assertThat(actual.getName()).isEqualTo(name);
        return this;
    }
    
    public ShoppingListGroupEntityAssert shoppingListItem(int index, Consumer<ShoppingListItemEntityAssert> consumer) {
        consumer.accept(ShoppingListItemEntityAssert.assertThat(actual.getShoppingListItems().get(index)));
        return this;
    }

    public ShoppingListGroupEntityAssert hasSize(int size) {
        Assertions.assertThat(actual.getShoppingListItems()).hasSize(size);
        return this;
    }
}
