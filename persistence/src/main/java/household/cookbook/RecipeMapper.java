package household.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RecipeMapper {

	private final IngredientMapper ingredientMapper;
	
	public Recipe map(RecipeEntity recipe) {
		List<Ingredient> ingredients = recipe.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toList());
		return new Recipe(recipe.getId(), recipe.getName(), recipe.getDescription(), ingredients);
	}

	public RecipeEntity map(Recipe recipe) {
		List<IngredientEntity> ingredients = recipe.getIngredients().stream().map(ingredientMapper::map).collect(Collectors.toList());
		return new RecipeEntity(recipe.getId(), recipe.getName(), "", ingredients);
	}
}
