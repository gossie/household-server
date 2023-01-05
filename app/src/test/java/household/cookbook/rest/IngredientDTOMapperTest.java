package household.cookbook.rest;

import static household.cookbook.domain.IngredientAssert.assertThat;
import static household.cookbook.rest.IngredientDTOAssert.assertThat;

import org.junit.jupiter.api.Test;

import household.cookbook.domain.Ingredient;

public class IngredientDTOMapperTest {

	private IngredientDTOMapper ingredientMapper;

	@Test
	public void testMap_toIngredientTO() throws Exception {
		ingredientMapper = new IngredientDTOMapper();

		Ingredient ingredient = new Ingredient("1L", 500.0, "g", "Hack");
		IngredientDTO result = ingredientMapper.map(ingredient);

		assertThat(result).hasAmount(500.0).hasUnit("g").hasName("Hack");
	}

	@Test
	public void testMap_toIngredient() throws Exception {
        ingredientMapper = new IngredientDTOMapper();

		IngredientDTO ingredient = new IngredientDTO(500.0, "g", "Hack");
		Ingredient result = ingredientMapper.map(ingredient);

		assertThat(result).hasAmount(500.0).hasUnit("g").hasName("Hack");
	}

}
