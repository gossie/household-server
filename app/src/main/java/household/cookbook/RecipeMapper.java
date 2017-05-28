package household.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RecipeMapper {

	private final IngredientMapper ingredientMapper;
	
	public RecipeDTO map(RecipeEntity recipe) {
		List<IngredientDTO> ingredients = recipe.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toList());
		return new RecipeDTO(recipe.getId(), recipe.getName(), ingredients);
	}

	public RecipeEntity map(RecipeDTO recipe) {
		List<IngredientEntity> ingredients = recipe.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toList());
		return new RecipeEntity(recipe.getName(), "", ingredients);
	}
}
