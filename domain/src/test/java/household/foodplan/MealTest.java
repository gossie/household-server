package household.foodplan;

import static household.foodplan.MealAssert.assertThat;

import org.junit.jupiter.api.Test;

public class MealTest {

    @Test
    public void testGetName_withRecipe() throws Exception {
        Meal meal = new Meal(2L, "Kartoffelbrei mit Fischstäbchen", new Recipe(5L, 3L));

        assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasRecipe(new Recipe(5L, 3L));
    }

    @Test
    public void testGetName_withoutRecipe() throws Exception {
        Meal meal = new Meal(1L, "Kartoffelbrei mit Fischstäbchen");
        assertThat(meal).hasName("Kartoffelbrei mit Fischstäbchen").hasNoRecipe();
    }
}
