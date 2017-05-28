package household.foodplan;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import household.cookbook.RecipeEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force = true)
@Getter
@EqualsAndHashCode
@ToString
public class MealEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	private String name;
	@OneToOne
	private RecipeEntity recipe;
	
	public MealEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public MealEntity(Long id, RecipeEntity recipe) {
		this.id = id;
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
