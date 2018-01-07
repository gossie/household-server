package household.cookbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import household.AbstractModel;

public class Cookbook extends AbstractModel {

	private List<Recipe> recipes;

    Cookbook(Long id, List<Recipe> recipes) {
		super(id);
		this.recipes = new ArrayList<>(recipes);
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

	public List<Recipe> getRecipes() {
		return Collections.unmodifiableList(recipes);
	}

	public void editRecipe(Long recipeId, Recipe recipe) {
		findRecipe(recipeId).ifPresent(r -> {
			r.setName(recipe.getName());
			r.setDescription(recipe.getDescription());
			r.setIngredients(recipe.getIngredients());
		});
	}

    public void deleteRecipe(Long recipeId) {
        recipes.removeIf(r -> Objects.equals(recipeId, r.getId()));
    }
}
