package household.cookbook;

import static household.cookbook.RecipeAssert.assertThat;
import static household.cookbook.RecipeDTOAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RecipeMapperTest {
	
	private RecipeMapper recipeMapper;

	@Test
	public void testMap_toRecipeDTO() throws Exception {
		recipeMapper = new RecipeMapper(new IngredientMapper());
		
		List<IngredientEntity> ingredients = Arrays.asList(new IngredientEntity(500, "g", "Hack"), new IngredientEntity(500, "ml", "Gemüsebrühe"));
		RecipeEntity recipe = new RecipeEntity("Chili", "", ingredients);
		
		RecipeDTO result = recipeMapper.map(recipe);
		
		assertThat(result)
				.hasSize(2)
				.ingredient(0, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("g").hasName("Hack"))
				.ingredient(1, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("ml").hasName("Gemüsebrühe"));
	}

	@Test
	public void testMap_toRecipe() throws Exception {
        recipeMapper = new RecipeMapper(new IngredientMapper());
		
		List<IngredientDTO> ingredients = Arrays.asList(new IngredientDTO(500, "g", "Hack"), new IngredientDTO(500, "ml", "Gemüsebrühe"));
		RecipeDTO recipe = new RecipeDTO(null, "Chili", ingredients);
		
		RecipeEntity result = recipeMapper.map(recipe);
		
		assertThat(result)
				.hasSize(2)
				.ingredient(0, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("g").hasName("Hack"))
				.ingredient(1, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("ml").hasName("Gemüsebrühe"));
	}

}
