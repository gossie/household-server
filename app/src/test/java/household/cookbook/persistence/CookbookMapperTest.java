package household.cookbook.persistence;

import static household.cookbook.domain.CookbookAssert.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import household.cookbook.domain.Cookbook;

public class CookbookMapperTest {

	private CookbookMapper cookbookMapper;

	@Test
	public void testMap_toCookbookDTO() throws Exception {
		cookbookMapper = new CookbookMapper(new RecipeMapper(new IngredientMapper()));

		CookbookEntity cookbook = createCookbook();

		Cookbook result = cookbookMapper.map(cookbook);

	    assertThat(result)
	        .hasId("6L")
	        .hasSize(2)
	        .recipe(0, r -> r.hasName("Recipe1").hasSize(2))
	        .recipe(1, r -> r.hasName("Recipe2").hasSize(2));
	}

	private CookbookEntity createCookbook() {
		RecipeEntity recipe1 = new RecipeEntity("1L", "Recipe1", "", asList(mock(IngredientEntity.class), mock(IngredientEntity.class)), "");
		RecipeEntity recipe2 = new RecipeEntity("2L", "Recipe2", "", asList(mock(IngredientEntity.class), mock(IngredientEntity.class)), "");

		CookbookEntity cookbook = new CookbookEntity("6L", asList(recipe1, recipe2));
		return cookbook;
	}

}
