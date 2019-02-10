package household.cookbook;

import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CookbookService {

	private final CookbookRepository cookbookRepository;

	public Cookbook getMinifiedCookbook(Long id) {
		Cookbook cookbook = cookbookRepository.determineCookbook(id);

		return cookbook.minify();
	}

	public Cookbook getCookbook(Long id) {
		return cookbookRepository.determineCookbook(id);
	}

	public Recipe getRecipe(Long cookbokId, Long recipeId) {
		return cookbookRepository.determineCookbook(cookbokId).findRecipe(recipeId).orElseThrow(IllegalArgumentException::new);
	}

	public Cookbook addRecipe(Long cookbookId, Recipe recipe) {
		Cookbook cookbook = cookbookRepository.determineCookbook(cookbookId);
		cookbook.addRecipe(recipe);
		Cookbook savedCookbook = cookbookRepository.saveCookbook(cookbook);
		return savedCookbook.minify();
	}

	public Cookbook editRecipe(Long cookbookId, Long recipeId, Recipe recipe) {
		Cookbook cookbook = cookbookRepository.determineCookbook(cookbookId);
		cookbook.editRecipe(recipeId, recipe);
		return cookbookRepository.saveCookbook(cookbook).minify();
	}

	public Cookbook deleteRecipe(Long cookbookId, Long recipeId) {
		Cookbook cookbook = cookbookRepository.determineCookbook(cookbookId);
		cookbook.deleteRecipe(recipeId);
		return cookbookRepository.saveCookbook(cookbook).minify();
	}

	public Cookbook createCookbook() {
		return cookbookRepository.createCookbook();
	}

    private void deleteCookbook(Long cookbookId) {
        cookbookRepository.deleteCookbook(cookbookId);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        deleteCookbook(event.getHousehold().getCookbookId());
    }
}
