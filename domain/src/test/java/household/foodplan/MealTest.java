package household.foodplan;

import static household.foodplan.MealAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import household.cookbook.Recipe;

public class MealTest {

	@Test
	public void testGetName_withRecipe() throws Exception {
		Recipe recipe = mock(Recipe.class);
		when(recipe.getName()).thenReturn("Kartoffelbrei mit Fischstäbchen");

		Meal meal = new Meal(2L, "Kartoffelbrei mit Fischstäbchen", 5L);

		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasRecipeId(5L);
	}

	@Test
	public void testGetName_withoutRecipe() throws Exception {
		Meal meal = new Meal(1L, "Kartoffelbrei mit Fischstäbchen");
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasNoRecipe();
	}
}
