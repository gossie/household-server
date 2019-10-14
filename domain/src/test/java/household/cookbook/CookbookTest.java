package household.cookbook;

import static household.cookbook.CookbookAssert.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class CookbookTest {

	@Test
	public void testEditRecipe() throws Exception {
		Recipe recipe1 = new Recipe(1L, "Recipe1", "", emptyList(), "");

		Recipe recipe2 = new Recipe(2L, "Recipe2", "", emptyList(), "");

		Cookbook cookbook = new Cookbook(3L, asList(recipe1, recipe2));

		Recipe changedRecipe = new Recipe(2L, "Recipe2_changed", "description", singletonList(mock(Ingredient.class)), "link_to_recipe");

		cookbook.editRecipe(2L, changedRecipe);

		assertThat(cookbook)
		    .hasSize(2)
		    .recipe(0, recipeAssert -> recipeAssert.hasName("Recipe1").hasDescription("").hasNoIngredients().hasNoUrl())
		    .recipe(1, recipeAssert -> recipeAssert.hasName("Recipe2_changed").hasDescription("description").hasUrl("link_to_recipe").hasSize(1));
	}

    @Test
	public void testDeleteRecipe() throws Exception {
		Recipe recipe1 = new Recipe(1L, "Recipe1", "", emptyList(), "");

		Recipe recipe2 = new Recipe(2L, "Recipe2", "", emptyList(), "");

		Cookbook cookbook = new Cookbook(3L, asList(recipe1, recipe2));

		cookbook.deleteRecipe(2L);

		assertThat(cookbook)
		    .hasSize(1)
		    .recipe(0, recipeAssert -> recipeAssert.hasName("Recipe1").hasDescription("").hasNoIngredients());
	}

}
