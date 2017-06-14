package household.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class RecipeDTOMapper {

	private final IngredientDTOMapper ingredientMapper;
	
	RecipeDTO map(Recipe recipe) {
		List<IngredientDTO> ingredients = recipe.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toList());
		return new RecipeDTO(recipe.getId(), recipe.getName(), ingredients);
	}

	Recipe map(RecipeDTO recipe) {
		List<Ingredient> ingredients = recipe.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toList());
		return new Recipe(recipe.getDatabaseId(), recipe.getName(), "", ingredients);
	}
}
