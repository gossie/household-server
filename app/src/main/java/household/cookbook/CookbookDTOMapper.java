package household.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CookbookDTOMapper {

	private final RecipeDTOMapper recipeMapper;
	
	public CookbookDTO map(Cookbook cookbook) {
		List<RecipeDTO> recipes = cookbook.getRecipes().stream()
				.map(recipeMapper::map)
				.collect(Collectors.toList());
		
		return new CookbookDTO(cookbook.getId(), recipes );
	}
}
