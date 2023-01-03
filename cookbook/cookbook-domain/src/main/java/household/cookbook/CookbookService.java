package household.cookbook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookbookService {

    private final CookbookRepository cookbookRepository;

	public Cookbook getMinifiedCookbook(String id) {
		Cookbook cookbook = cookbookRepository.determineCookbook(id);

		return cookbook.minify();
	}

	public Cookbook getCookbook(String id) {
		return cookbookRepository.determineCookbook(id);
	}

	public Recipe getRecipe(String cookbokId, String recipeId) {
		return cookbookRepository.determineCookbook(cookbokId).findRecipe(recipeId).orElseThrow(IllegalArgumentException::new);
	}

	public Cookbook addRecipe(String cookbookId, Recipe recipe) {
		Cookbook cookbook = cookbookRepository.determineCookbook(cookbookId);
		cookbook.addRecipe(recipe);
		Cookbook savedCookbook = cookbookRepository.saveCookbook(cookbook);
		return savedCookbook.minify();
	}

	public Cookbook editRecipe(String cookbookId, String recipeId, Recipe recipe) {
		Cookbook cookbook = cookbookRepository.determineCookbook(cookbookId);
		cookbook.editRecipe(recipeId, recipe);
		return cookbookRepository.saveCookbook(cookbook).minify();
	}

	public Cookbook deleteRecipe(String cookbookId, String recipeId) {
		Cookbook cookbook = cookbookRepository.determineCookbook(cookbookId);
		cookbook.deleteRecipe(recipeId);
		return cookbookRepository.saveCookbook(cookbook).minify();
	}

	public Cookbook createCookbook() {
		return cookbookRepository.createCookbook();
	}

    public void deleteCookbook(String cookbookId) {
        cookbookRepository.deleteCookbook(cookbookId);
    }
}
