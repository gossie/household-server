package household.cookbook;

import static household.cookbook.CookbookDTOAssert.assertThat;
import static household.cookbook.RecipeDTOAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class CookbookDTOMapperTest {
	
	private CookbookDTOMapper cookbookMapper;

	@Test
	public void testMap_toCookbookDTO() throws Exception {
		cookbookMapper = new CookbookDTOMapper(new RecipeDTOMapper(new IngredientDTOMapper()));
		
		Cookbook cookbook = createCookbook();
		
		CookbookDTO result = cookbookMapper.map(cookbook);
		
	    assertThat(result).hasDatabaseId(6L).hasSize(2);
	    assertThat(result.getRecipes().get(0)).hasName("Recipe1").hasSize(2);
	    assertThat(result.getRecipes().get(1)).hasName("Recipe2").hasSize(2);
	}

	private Cookbook createCookbook() {
		Recipe recipe1 = new Recipe(1L, "Recipe1", "", asList(mock(Ingredient.class), mock(Ingredient.class)), "");
		Recipe recipe2 = new Recipe(2L, "Recipe2", "", asList(mock(Ingredient.class), mock(Ingredient.class)), "");
		
		Cookbook cookbook = new Cookbook(6L, asList(recipe1, recipe2));
		return cookbook;
	}

}
