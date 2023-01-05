package household.cookbook.persistence;

import java.util.List;
import java.util.stream.Collectors;

import household.cookbook.domain.Ingredient;
import household.cookbook.domain.Recipe;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class RecipeMapper {

	private final IngredientMapper ingredientMapper;

	Recipe map(RecipeEntity recipe) {
		List<Ingredient> ingredients = recipe.getIngredients().stream()
            .map(ingredientMapper::map)
            .collect(Collectors.toList());

		return new Recipe(recipe.getId(), recipe.getName(), recipe.getDescription(), ingredients, recipe.getUrl());
	}

	RecipeEntity map(Recipe recipe) {
		List<IngredientEntity> ingredients = recipe.getIngredients().stream()
            .map(ingredientMapper::map)
            .collect(Collectors.toList());

		return new RecipeEntity(recipe.getId(), recipe.getName(), "", ingredients, recipe.getUrl());
	}
}
