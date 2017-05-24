package household.cookbook;

import static household.cookbook.CookbookAssert.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class CookbookTest {

	@Test
	public void testEditRecipe() throws Exception {
		Recipe recipe1 = spy(new Recipe("Recipe1", "", emptyList()));
		when(recipe1.getId()).thenReturn(1L);
		
		Recipe recipe2 = spy(new Recipe("Recipe2", "", emptyList()));
		when(recipe2.getId()).thenReturn(2L);
		
		Cookbook cookbook = new Cookbook(asList(recipe1, recipe2));
		
		Recipe changedRecipe = spy(new Recipe("Recipe2_changed", "description", singletonList(mock(Ingredient.class))));
		when(changedRecipe.getId()).thenReturn(2L);
		
		cookbook.editRecipe(2L, changedRecipe);
		
		assertThat(cookbook)
		    .hasSize(2)
		    .recipe(0, recipeAssert -> recipeAssert.hasName("Recipe1").hasDescription("").hasNoIngredients())
		    .recipe(1, recipeAssert -> recipeAssert.hasName("Recipe2_changed").hasDescription("description").hasSize(1));
	}

}
