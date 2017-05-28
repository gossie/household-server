package household.cookbook;

import static household.cookbook.IngredientAssert.assertThat;
import static household.cookbook.IngredientEntityAssert.assertThat;

import org.junit.Test;

public class IngredientMapperTest {
	
	private IngredientMapper ingredientMapper;

	@Test
	public void testMap_toIngredientTO() throws Exception {
		ingredientMapper = new IngredientMapper();
		
		IngredientEntity ingredient = new IngredientEntity(1L, 500.0, "g", "Hack");
		Ingredient result = ingredientMapper.map(ingredient);
		
		assertThat(result).hasId(1L).hasAmount(500.0).hasUnit("g").hasName("Hack");
	}
	
	@Test
	public void testMap_toIngredient() throws Exception {
        ingredientMapper = new IngredientMapper();
		
		Ingredient ingredient = new Ingredient(1L, 500.0, "g", "Hack");
		IngredientEntity result = ingredientMapper.map(ingredient);
		
		assertThat(result).hasAmount(500.0).hasUnit("g").hasName("Hack");
	}

}
