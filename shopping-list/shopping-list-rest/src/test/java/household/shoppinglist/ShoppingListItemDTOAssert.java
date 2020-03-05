package household.shoppinglist;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.springframework.hateoas.Link;

public class ShoppingListItemDTOAssert extends AbstractAssert<ShoppingListItemDTOAssert, ShoppingListItemDTO> {

	private ShoppingListItemDTOAssert(ShoppingListItemDTO actual) {
		super(actual, ShoppingListItemDTOAssert.class);
	}

	public static ShoppingListItemDTOAssert assertThat(ShoppingListItemDTO actual) {
		return new ShoppingListItemDTOAssert(actual);
	}

	public ShoppingListItemDTOAssert hasName(String name) {
		Assertions.assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ShoppingListItemDTOAssert isSelected() {
		Assertions.assertThat(actual.isSelected()).isTrue();
		return this;
	}

	public ShoppingListItemDTOAssert isDeselected() {
		Assertions.assertThat(actual.isSelected()).isFalse();
		return this;
	}

    public ShoppingListItemDTOAssert hasLink(String rel, String href) {
	    Assertions.assertThat(actual.getLink(rel)).map(Link::getHref).contains(href);
	    return this;
    }
}
