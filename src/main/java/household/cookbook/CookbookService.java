package household.cookbook;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CookbookService {
	
	private final CookbookRepository cookbookRepository;

	public Cookbook getMinifiedCookbook(Long id) {
		Cookbook cookbook = cookbookRepository.findOne(id);
		
		return cookbook.minify();
	}

	public Cookbook getCookbook(Long id) {
		return cookbookRepository.findOne(id);
	}

	public Recipe getRecipe(Long cookbokId, Long recipeId) {
		return cookbookRepository.findOne(cookbokId).findRecipe(recipeId).orElseThrow(IllegalArgumentException::new);
	}

	public Cookbook addRecipe(Long cookbookId, Recipe recipe) {
		Cookbook cookbook = cookbookRepository.findOne(cookbookId);
		cookbook.addRecipe(recipe);
		Cookbook savedCookbook = cookbookRepository.save(cookbook);
		return savedCookbook.minify();
	}

	public Cookbook editRecipe(Long cookbookId, Long recipeId, Recipe recipe) {
		Cookbook cookbook = cookbookRepository.findOne(cookbookId);
		cookbook.editRecipe(recipeId, recipe);
		return cookbookRepository.save(cookbook).minify();
	}
	
}
