package household.shoppinglist;

import java.util.function.Consumer;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ShoppingListGroupDTOAssert extends AbstractAssert<ShoppingListGroupDTOAssert, ShoppingListGroupDTO> {

    private ShoppingListGroupDTOAssert(ShoppingListGroupDTO actual) {
        super(actual, ShoppingListGroupDTOAssert.class);
    }

    public static final ShoppingListGroupDTOAssert assertThat(ShoppingListGroupDTO actual) {
        return new ShoppingListGroupDTOAssert(actual);
    }

    public ShoppingListGroupDTOAssert hasName(String name) {
        Assertions.assertThat(actual.getName()).isEqualTo(name);
        return this;
    }
    
    public ShoppingListGroupDTOAssert shoppingListItem(int index, Consumer<ShoppingListItemDTOAssert> consumer) {
        consumer.accept(ShoppingListItemDTOAssert.assertThat(actual.getShoppingListItems().get(index)));
        return this;
    }

    public ShoppingListGroupDTOAssert hasSize(int size) {
        Assertions.assertThat(actual.getShoppingListItems()).hasSize(size);
        return this;
    }
}
