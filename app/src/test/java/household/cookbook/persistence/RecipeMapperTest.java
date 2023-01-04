package household.cookbook.persistence;

import static household.cookbook.domain.RecipeAssert.assertThat;
import static household.cookbook.persistence.RecipeEntityAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import household.cookbook.domain.Ingredient;
import household.cookbook.domain.Recipe;

public class RecipeMapperTest {

	private RecipeMapper recipeMapper;

	@Test
	public void testMap_toRecipeDTO() throws Exception {
		recipeMapper = new RecipeMapper(new IngredientMapper());

		List<IngredientEntity> ingredients = Arrays.asList(new IngredientEntity("1L", 500, "g", "Hack"), new IngredientEntity("2L", 500, "ml", "Gemüsebrühe"));
		RecipeEntity recipe = new RecipeEntity("3L", "Chili", "", ingredients, "");

		Recipe result = recipeMapper.map(recipe);

		assertThat(result)
		        .hasId("3L")
				.hasSize(2)
				.ingredient(0, ingredientAssert -> ingredientAssert.hasId("1L").hasAmount(500).hasUnit("g").hasName("Hack"))
				.ingredient(1, ingredientAssert -> ingredientAssert.hasId("2L").hasAmount(500).hasUnit("ml").hasName("Gemüsebrühe"));
	}

	@Test
	public void testMap_toRecipe() throws Exception {
        recipeMapper = new RecipeMapper(new IngredientMapper());

		List<Ingredient> ingredients = Arrays.asList(new Ingredient("1L", 500, "g", "Hack"), new Ingredient("2L", 500, "ml", "Gemüsebrühe"));
		Recipe recipe = new Recipe("3L", null, "Chili", ingredients, "");

		RecipeEntity result = recipeMapper.map(recipe);

		assertThat(result)
				.hasSize(2)
				.ingredient(0, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("g").hasName("Hack"))
				.ingredient(1, ingredientAssert -> ingredientAssert.hasAmount(500).hasUnit("ml").hasName("Gemüsebrühe"));
	}

}
