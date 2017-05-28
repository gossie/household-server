package household.foodplan;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import household.AbstractEntity;
import household.cookbook.RecipeEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class MealEntity extends AbstractEntity {

	private String name;
	@OneToOne
	private RecipeEntity recipe;
	
	public MealEntity(String name) {
		this.name = name;
	}
	
	public MealEntity(RecipeEntity recipe) {
		this.recipe = recipe;
	}
	
	public Optional<RecipeEntity> getRecipe() {
		return Optional.ofNullable(recipe);
	}
	
	public String getName() {
		return getRecipe().map(RecipeEntity::getName).orElse(name);
	}
	
	public void clear() {
		name = "";
		recipe = null;
	}
}
