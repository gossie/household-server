package household.cookbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cookbook extends AbstractModel {

	private List<Recipe> recipes;

    Cookbook(String id, List<Recipe> recipes) {
		super(id);
		this.recipes = new ArrayList<>(recipes);
	}

	public Cookbook minify() {
		List<Recipe> minifiedRecipes = recipes.stream()
				.map(Recipe::minify)
				.collect(Collectors.toList());

		return new Cookbook(getId(), minifiedRecipes);
	}

	public Optional<Recipe> findRecipe(String recipeId) {
		return recipes.stream()
            .filter(r -> Objects.equals(recipeId, r.getId()))
            .findFirst();
	}

	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}

	public List<Recipe> getRecipes() {
		return Collections.unmodifiableList(recipes);
	}

	public void editRecipe(String recipeId, Recipe recipe) {
		findRecipe(recipeId).ifPresent(r -> {
			r.setName(recipe.getName());
			r.setUrl(recipe.getUrl());
			r.setDescription(recipe.getDescription());
			r.setIngredients(recipe.getIngredients());
		});
	}

    public void deleteRecipe(String recipeId) {
        recipes.removeIf(r -> Objects.equals(recipeId, r.getId()));
    }
}
