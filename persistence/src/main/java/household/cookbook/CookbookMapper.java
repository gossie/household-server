package household.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class CookbookMapper {

	private final RecipeMapper recipeMapper;
	
	Cookbook map(CookbookEntity cookbook) {
		List<Recipe> recipes = cookbook.getRecipes().stream()
				.map(recipeMapper::map)
				.collect(Collectors.toList());
		
		return new Cookbook(cookbook.getId(), recipes );
	}
	
	CookbookEntity map(Cookbook cookbook) {
		List<RecipeEntity> recipes = cookbook.getRecipes().stream()
				.map(recipeMapper::map)
				.collect(Collectors.toList());
		
		return new CookbookEntity(cookbook.getId(), recipes);
	}
}
