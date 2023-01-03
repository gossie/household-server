package household.cookbook;

import static household.cookbook.RecipeAssert.assertThat;
import static household.cookbook.RecipeDTOAssert.assertThat;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RecipeDTOMapperTest {

	private RecipeDTOMapper recipeMapper;

	@Test
	public void testMap_toRecipeDTO() throws Exception {
		recipeMapper = new RecipeDTOMapper(new IngredientDTOMapper());

		List<Ingredient> ingredients = asList(new Ingredient("1L", 500, "g", "Hack"), new Ingredient("2L", 500, "ml", "Gemüsebrühe"));
		Recipe recipe = new Recipe("3L", "Chili", "", ingredients, "");

		RecipeDTO result = recipeMapper.map(recipe);

		assertThat(result)
				.hasSize(2)
				.ingredient(0, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("g").hasName("Hack"))
				.ingredient(1, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("ml").hasName("Gemüsebrühe"));
	}

	@Test
	public void testMap_toRecipe() throws Exception {
        recipeMapper = new RecipeDTOMapper(new IngredientDTOMapper());

		List<IngredientDTO> ingredients = Arrays.asList(new IngredientDTO(500, "g", "Hack"), new IngredientDTO(500, "ml", "Gemüsebrühe"));
		RecipeDTO recipe = new RecipeDTO(null, "Chili", ingredients, "");

		Recipe result = recipeMapper.map(recipe);

		assertThat(result)
				.hasSize(2)
				.ingredient(0, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("g").hasName("Hack"))
				.ingredient(1, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("ml").hasName("Gemüsebrühe"));
	}

}
