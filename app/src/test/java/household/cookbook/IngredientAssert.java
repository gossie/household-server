package household.cookbook;

import static org.junit.Assert.assertEquals;

import org.assertj.core.api.AbstractAssert;

import household.cookbook.IngredientEntity;

public class IngredientAssert extends AbstractAssert<IngredientAssert, IngredientEntity> {

	private IngredientAssert(IngredientEntity actual) {
		super(actual, IngredientAssert.class);
	}

	public static IngredientAssert assertThat(IngredientEntity actual) {
		return new IngredientAssert(actual);
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
