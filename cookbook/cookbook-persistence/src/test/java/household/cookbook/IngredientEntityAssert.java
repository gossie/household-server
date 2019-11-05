package household.cookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.AbstractAssert;

import household.cookbook.IngredientEntity;

public class IngredientEntityAssert extends AbstractAssert<IngredientEntityAssert, IngredientEntity> {

	private IngredientEntityAssert(IngredientEntity actual) {
		super(actual, IngredientEntityAssert.class);
	}

	public static IngredientEntityAssert assertThat(IngredientEntity actual) {
		return new IngredientEntityAssert(actual);
	}

	public IngredientEntityAssert hasAmount(double amount) {
		assertEquals(amount, actual.getAmount(), 0.001);
		return this;
	}

	public IngredientEntityAssert hasUnit(String unit) {
		assertEquals(unit, actual.getUnit());
		return this;
	}

	public IngredientEntityAssert hasName(String name) {
		assertEquals(name, actual.getName());
		return this;
	}
}
