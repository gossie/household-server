package household.cookbook.rest;

import java.util.List;
import java.util.stream.Collectors;

import household.cookbook.domain.Ingredient;
import household.cookbook.domain.Recipe;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class RecipeDTOMapper {

	private final IngredientDTOMapper ingredientMapper;

	RecipeDTO map(Recipe recipe) {
		List<IngredientDTO> ingredients = recipe.getIngredients().stream()
            .map(ingredientMapper::map)
            .collect(Collectors.toList());

		return new RecipeDTO(recipe.getId(), recipe.getName(), ingredients, recipe.getUrl());
	}

	Recipe map(RecipeDTO recipe) {
		List<Ingredient> ingredients = recipe.getIngredients().stream()
            .map(ingredientMapper::map)
            .collect(Collectors.toList());

		return new Recipe(recipe.getDatabaseId(), recipe.getName(), "", ingredients, recipe.getUrl());
	}
}
