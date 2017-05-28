package household.foodplan;

import java.util.Optional;

import household.AbstractModel;
import household.cookbook.Recipe;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Meal extends AbstractModel {

	private String name;
	private Recipe recipe;
	
    Meal(Long id, String name) {
		super(id);
		this.name = name;
	}
	
	public Meal(Long id, Recipe recipe) {
		super(id);
		this.recipe = recipe;
	}
	
	public Optional<Recipe> getRecipe() {
		return Optional.ofNullable(recipe);
	}
	
	public String getName() {
		return getRecipe().map(Recipe::getName).orElse(name);
	}
	
	public void clear() {
		name = "";
		recipe = null;
	}
}
