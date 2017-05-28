package household.cookbook;

import static household.cookbook.CookbookEntityAssert.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class CookbookEntityTest {

	@Test
	public void testEditRecipe() throws Exception {
		RecipeEntity recipe1 = new RecipeEntity(1L, "Recipe1", "", emptyList());
		
		RecipeEntity recipe2 = new RecipeEntity(2L, "Recipe2", "", emptyList());
		
		CookbookEntity cookbook = new CookbookEntity(3L, asList(recipe1, recipe2));
		
		RecipeEntity changedRecipe = new RecipeEntity(2L, "Recipe2_changed", "description", singletonList(mock(IngredientEntity.class)));
		
		cookbook.editRecipe(2L, changedRecipe);
		
		assertThat(cookbook)
		    .hasSize(2)
		    .recipe(0, recipeAssert -> recipeAssert.hasName("Recipe1").hasDescription("").hasNoIngredients())
		    .recipe(1, recipeAssert -> recipeAssert.hasName("Recipe2_changed").hasDescription("description").hasSize(1));
	}

}
