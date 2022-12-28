package household.cookbook;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.Link;

@RestController
@RequestMapping("/api/cookbooks")
@CrossOrigin
@RequiredArgsConstructor
public class CookbookController {

	private final CookbookDTOMapper cookbookMapper;
	private final RecipeDTOMapper recipeMapper;
	private final CookbookService cookbookService;

	@GetMapping(path="/{cookbookId}", produces={"application/vnd.household.min.v1+json"})
	public CookbookDTO getMinifiedRecipes(@PathVariable Long cookbookId) {
		return addLinks(createResource(cookbookService.getMinifiedCookbook(cookbookId)));
	}

	@GetMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.v1+json"})
	public RecipeDTO getRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		return addRecipeSelfLink(cookbookId, createResource(cookbookService.getRecipe(cookbookId, recipeId)));
	}

	@PutMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.min.v1+json"}, consumes={"application/vnd.household.v1+json"})
	public CookbookDTO editRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId, @RequestBody RecipeDTO recipe) {
		return addLinks(createResource(cookbookService.editRecipe(cookbookId, recipeId, recipeMapper.map(recipe))));
	}

    @DeleteMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.min.v1+json"})
	public CookbookDTO deleteRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		return addLinks(createResource(cookbookService.deleteRecipe(cookbookId, recipeId)));
	}

	@PostMapping(path="/{cookbookId}/recipes", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.min.v1+json"})
	public CookbookDTO addRecipe(@PathVariable Long cookbookId, @RequestBody RecipeDTO recipe) {
		return addLinks(createResource(cookbookService.addRecipe(cookbookId, recipeMapper.map(recipe))));
	}

	private CookbookDTO createResource(Cookbook cookbook) {
		return cookbookMapper.map(cookbook);
	}

	private RecipeDTO createResource(Recipe recipe) {
		return recipeMapper.map(recipe);
	}

    private CookbookDTO addLinks(CookbookDTO cookbook) {
        return addCookbookSelfLink(createRecipeLink(cookbook)).getRecipes().stream()
            .map(recipe -> addRecipeSelfLink(cookbook.getDatabaseId(), recipe))
            .reduce(cookbook, (c, r) -> c, (r, c) -> c);
    }

    private RecipeDTO addRecipeSelfLink(Long cookbookId, RecipeDTO recipe) {
        return (RecipeDTO) recipe.add(Link.of("/api/cookbooks/" + cookbookId + "/recipes/" + recipe.getDatabaseId()));
    }

    private CookbookDTO addCookbookSelfLink(CookbookDTO cookbook) {
        return (CookbookDTO) cookbook.add(Link.of("/api/cookbooks/" + cookbook.getDatabaseId()));
    }

    private CookbookDTO createRecipeLink(CookbookDTO cookbook) {
        return (CookbookDTO) cookbook.add(Link.of("/api/cookbooks/" + cookbook.getDatabaseId() + "/recipes", "create"));
    }
}
