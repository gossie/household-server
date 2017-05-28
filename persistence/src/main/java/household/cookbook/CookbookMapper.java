package household.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CookbookMapper {

	private final RecipeMapper recipeMapper;
	
	public Cookbook map(CookbookEntity cookbook) {
		List<Recipe> recipes = cookbook.getRecipes().stream()
				.map(recipeMapper::map)
				.collect(Collectors.toList());
		
		return new Cookbook(cookbook.getId(), recipes );
	}
}
