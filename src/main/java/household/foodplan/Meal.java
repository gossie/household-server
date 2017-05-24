package household.foodplan;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import household.AbstractEntity;
import household.cookbook.Recipe;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class Meal extends AbstractEntity {

	private String name;
	@OneToOne
	private Recipe recipe;
	
	public Meal(String name) {
		this.name = name;
	}
	
	public Meal(Recipe recipe) {
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
