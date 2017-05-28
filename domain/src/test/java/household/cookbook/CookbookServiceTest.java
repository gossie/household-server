package household.cookbook;

import static household.cookbook.CookbookAssert.assertThat;
import static household.cookbook.RecipeAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

public class CookbookServiceTest {
	
	private CookbookService cookbookService;

	@Test
	public void testGetMinifiedRecipes() throws Exception {
	    Cookbook cookbook = createCookbook();
		CookbookRepository cookbookRepository = mock(CookbookRepository.class);
		when(cookbookRepository.determineCookbook(6L)).thenReturn(cookbook);
		
		cookbookService = new CookbookService(cookbookRepository);
		Cookbook actualCookbook = cookbookService.getMinifiedCookbook(6L);
		
		assertThat(actualCookbook).hasSize(2);
		
		Recipe recipe1 = actualCookbook.getRecipes().get(0);
		assertThat(recipe1).hasName("Recipe1").hasNoIngredients();
		
		Recipe recipe2 = actualCookbook.getRecipes().get(1);
		assertThat(recipe2).hasName("Recipe2").hasNoIngredients();
	}
	
	@Test
	public void testGetRecipe() throws Exception {
		Cookbook cookbook = createCookbook();
		CookbookRepository cookbookRepository = mock(CookbookRepository.class);
		when(cookbookRepository.determineCookbook(6L)).thenReturn(cookbook);
		
		cookbookService = new CookbookService(cookbookRepository);
		Recipe result = cookbookService.getRecipe(6L, 2L);
		
		assertThat(result).hasName("Recipe2").hasSize(2);
	}

	@Test
	public void testAddRecipe() throws Exception {
		Cookbook cookbook = createCookbook();
		CookbookRepository cookbookRepository = mock(CookbookRepository.class);
		when(cookbookRepository.determineCookbook(18L)).thenReturn(cookbook);
		when(cookbookRepository.saveCookbook(cookbook)).thenReturn(cookbook);
		
		Recipe recipeToAdd = new Recipe(1L, "Recipe3", "Description", asList(mock(Ingredient.class), mock(Ingredient.class)));
		
		cookbookService = new CookbookService(cookbookRepository);
		Cookbook result = cookbookService.addRecipe(18L, recipeToAdd);
		
        assertThat(result)
            .hasSize(3)
            .recipe(0, recipeAssert -> recipeAssert.hasName("Recipe1").hasNoIngredients())
            .recipe(1, recipeAssert -> recipeAssert.hasName("Recipe2").hasNoIngredients())
            .recipe(2, recipeAssert -> recipeAssert.hasName("Recipe3").hasNoIngredients());
	}

	@Test
	public void testEditRecipe() throws Exception {
		Cookbook cookbook = createCookbook();
		CookbookRepository cookbookRepository = mock(CookbookRepository.class);
		when(cookbookRepository.determineCookbook(8L)).thenReturn(cookbook);
		when(cookbookRepository.saveCookbook(cookbook)).thenReturn(cookbook);
		
		cookbookService = new CookbookService(cookbookRepository);
		Cookbook result = cookbookService.editRecipe(8L, 15L, new Recipe(1L, "Recipe1", "", asList(mock(Ingredient.class), mock(Ingredient.class), mock(Ingredient.class))));
		
		assertThat(result)
		    .hasSize(2)
		    .recipe(0, recipeAssert -> recipeAssert.hasName("Recipe1").hasNoIngredients())
            .recipe(1, recipeAssert -> recipeAssert.hasName("Recipe2").hasNoIngredients());
	}
	
	private Cookbook createCookbook() {
		Recipe recipe1 = new Recipe(1L, "Recipe1", "", asList(mock(Ingredient.class), mock(Ingredient.class)));
		Recipe recipe2 = new Recipe(2L, "Recipe2", "", asList(mock(Ingredient.class), mock(Ingredient.class)));
		
		Cookbook cookbook = new Cookbook(6L, new ArrayList<>(asList(recipe1, recipe2)));
		return cookbook;
	}

}
