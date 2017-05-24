package household.cookbook;

import static household.cookbook.CookbookDTOAssert.assertThat;
import static household.cookbook.RecipeDTOAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class CookbookMapperTest {
	
	private CookbookMapper cookbookMapper;

	@Test
	public void testMap_toCookbookDTO() throws Exception {
		cookbookMapper = new CookbookMapper(new RecipeMapper(new IngredientMapper()));
		
		Cookbook cookbook = createCookbook();
		
		CookbookDTO result = cookbookMapper.map(cookbook);
		
	    assertThat(result).hasDatabaseId(6L).hasSize(2);
	    assertThat(result.getRecipes().get(0)).hasName("Recipe1").hasSize(2);
	    assertThat(result.getRecipes().get(1)).hasName("Recipe2").hasSize(2);
	}

	private Cookbook createCookbook() {
		Recipe recipe1 = spy(new Recipe("Recipe1", "", asList(mock(Ingredient.class), mock(Ingredient.class))));
		when(recipe1.getId()).thenReturn(1L);
		Recipe recipe2 = spy(new Recipe("Recipe2", "", asList(mock(Ingredient.class), mock(Ingredient.class))));
		when(recipe2.getId()).thenReturn(2L);
		
		Cookbook cookbook = spy(new Cookbook(asList(recipe1, recipe2)));
		when(cookbook.getId()).thenReturn(6L);
		return cookbook;
	}

}
