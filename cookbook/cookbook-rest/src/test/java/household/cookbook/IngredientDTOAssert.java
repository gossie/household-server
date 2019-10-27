package household.cookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.AbstractAssert;

import household.cookbook.IngredientDTO;

public class IngredientDTOAssert extends AbstractAssert<IngredientDTOAssert, IngredientDTO> {

	private IngredientDTOAssert(IngredientDTO actual) {
		super(actual, IngredientDTOAssert.class);
	}

	public static IngredientDTOAssert assertThat(IngredientDTO actual) {
		return new IngredientDTOAssert(actual);
	}

	public IngredientDTOAssert hasAmount(double amount) {
		assertEquals(amount, actual.getAmount(), 0.001);
		return this;
	}

	public IngredientDTOAssert hasUnit(String unit) {
		assertEquals(unit, actual.getUnit());
		return this;
	}

	public IngredientDTOAssert hasName(String name) {
		assertEquals(name, actual.getName());
		return this;
	}
}
