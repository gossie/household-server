package household.cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class Cookbook extends AbstractEntity {

	@OneToMany(cascade=CascadeType.ALL)
	private List<Recipe> recipes = new ArrayList<>();
	
	private Cookbook(Long id, List<Recipe> recipes) {
		super(id);
		this.recipes = recipes;
	}

	public Cookbook minify() {
		List<Recipe> minifiedRecipes = recipes.stream()
				.map(r -> r.minify())
				.collect(Collectors.toList());
		
		return new Cookbook(getId(), minifiedRecipes);
	}

	public Optional<Recipe> findRecipe(Long recipeId) {
		return recipes.stream().filter(r -> Objects.equals(recipeId, r.getId())).findFirst();
	}

	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}

	public void editRecipe(Long recipeId, Recipe recipe) {
		findRecipe(recipeId).ifPresent(r -> {
			r.setName(recipe.getName());
			r.setDescription(recipe.getDescription());
			r.setIngredients(recipe.getIngredients());
		});
		
	}
}
