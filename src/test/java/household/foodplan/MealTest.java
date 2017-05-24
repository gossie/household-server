package household.foodplan;

import static household.foodplan.MealAssert.assertThat;

import java.util.Collections;

import org.junit.Test;

import household.cookbook.Recipe;

public class MealTest {

	@Test
	public void testGetName_withRecipe() throws Exception {
		Recipe recipe = new Recipe("Kartoffelbrei mit Fischstäbchen", "", Collections.emptyList());
		Meal meal = new Meal(recipe);
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasRecipe(recipe);
	}
	
	@Test
	public void testGetName_withoutRecipe() throws Exception {
		Meal meal = new Meal("Kartoffelbrei mit Fischstäbchen");
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasNoRecipe();
	}
}
