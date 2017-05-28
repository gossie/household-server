package household.foodplan;

import static household.foodplan.MealEntityAssert.assertThat;

import java.util.Collections;

import org.junit.Test;

import household.cookbook.RecipeEntity;

public class MealEntityTest {

	@Test
	public void testGetName_withRecipe() throws Exception {
		RecipeEntity recipe = new RecipeEntity(1L, "Kartoffelbrei mit Fischstäbchen", "", Collections.emptyList());
		MealEntity meal = new MealEntity(2L, recipe);
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasRecipe(recipe);
	}
	
	@Test
	public void testGetName_withoutRecipe() throws Exception {
		MealEntity meal = new MealEntity(1L, "Kartoffelbrei mit Fischstäbchen");
		assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasNoRecipe();
	}
}
