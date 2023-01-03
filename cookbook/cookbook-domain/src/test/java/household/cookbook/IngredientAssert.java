package household.cookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.AbstractAssert;

public class IngredientAssert extends AbstractAssert<IngredientAssert, Ingredient> {

	private IngredientAssert(Ingredient actual) {
		super(actual, IngredientAssert.class);
	}

	public static IngredientAssert assertThat(Ingredient actual) {
		return new IngredientAssert(actual);
	}

	public IngredientAssert hasId(String id) {
		assertEquals(id, actual.getId());
		return this;
	}

	public IngredientAssert hasAmount(double amount) {
		assertEquals(amount, actual.getAmount(), 0.0);
		return this;
	}

	public IngredientAssert hasUnit(String unit) {
		assertEquals(unit, actual.getUnit());
		return this;
	}

	public IngredientAssert hasName(String name) {
		assertEquals(name, actual.getName());
		return this;
	}
}
