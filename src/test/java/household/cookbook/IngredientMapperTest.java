package household.cookbook;

import static household.cookbook.IngredientDTOAssert.assertThat;
import static household.cookbook.IngredientAssert.assertThat;

import org.junit.Test;

import household.cookbook.Ingredient;
import household.cookbook.IngredientDTO;
import household.cookbook.IngredientMapper;

public class IngredientMapperTest {
	
	private IngredientMapper ingredientMapper;

	@Test
	public void testMap_toIngredientTO() throws Exception {
		ingredientMapper = new IngredientMapper();
		
		Ingredient ingredient = new Ingredient(500.0, "g", "Hack");
		IngredientDTO result = ingredientMapper.map(ingredient);
		
		assertThat(result).hasAmount(500.0).hasUnit("g").hasName("Hack");
	}
	
	@Test
	public void testMap_toIngredient() throws Exception {
        ingredientMapper = new IngredientMapper();
		
		IngredientDTO ingredient = new IngredientDTO(500.0, "g", "Hack");
		Ingredient result = ingredientMapper.map(ingredient);
		
		assertThat(result).hasAmount(500.0).hasUnit("g").hasName("Hack");
	}

}
