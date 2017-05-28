package household.foodplan;

import static household.foodplan.MealAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import household.cookbook.Recipe;

public class MealTest {

	@Test
	public void testGetName_withRecipe() throws Exception {
		Recipe recipe = mock(Recipe.class);
		when(recipe.getName()).thenReturn("Kartoffelbrei mit Fischstäbchen");
		
		Meal meal = new Meal(2L, recipe);
		
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasRecipe(recipe);
	}
	
	@Test
	public void testGetName_withoutRecipe() throws Exception {
		Meal meal = new Meal(1L, "Kartoffelbrei mit Fischstäbchen");
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasNoRecipe();
	}
}
