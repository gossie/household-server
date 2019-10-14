package household.cookbook;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/cookbooks")
@ExposesResourceFor(CookbookDTO.class)
@CrossOrigin
@RequiredArgsConstructor
public class CookbookController {

	private final CookbookDTOMapper cookbookMapper;
	private final RecipeDTOMapper recipeMapper;
	private final CookbookService cookbookService;
	private final CookbookResourceProcessor cookbookResourceProcessor;
	private final EntityLinks entityLinks;

	@GetMapping(path="/{cookbookId}", produces={"application/vnd.household.min.v1+json"})
	public HttpEntity<Resource<CookbookDTO>> getMinifiedRecipes(@PathVariable Long cookbookId) {
		Cookbook minifiedCookbook = cookbookService.getMinifiedCookbook(cookbookId);
		return ResponseEntity.ok(createResource(minifiedCookbook));
	}

	@GetMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<RecipeDTO>> getRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		Recipe recipe = cookbookService.getRecipe(cookbookId, recipeId);
		return ResponseEntity.ok(createResource(cookbookId, recipe));
	}

	@PutMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.min.v1+json"}, consumes={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<CookbookDTO>> editRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId, @RequestBody RecipeDTO recipe) {
		Cookbook minifiedCookbook = cookbookService.editRecipe(cookbookId, recipeId, recipeMapper.map(recipe));
		return ResponseEntity.ok(createResource(minifiedCookbook));
	}

    @DeleteMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.min.v1+json"})
	public HttpEntity<Resource<CookbookDTO>> deleteRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		Cookbook minifiedCookbook = cookbookService.deleteRecipe(cookbookId, recipeId);
		return ResponseEntity.ok(createResource(minifiedCookbook));
	}

	@PostMapping(path="/{cookbookId}/recipes", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.min.v1+json"})
	public HttpEntity<Resource<CookbookDTO>> addRecipe(@PathVariable Long cookbookId, @RequestBody RecipeDTO recipe) {
		Cookbook minifiedCookbook = cookbookService.addRecipe(cookbookId, recipeMapper.map(recipe));
		return ResponseEntity.ok(createResource(minifiedCookbook));
	}

	private Resource<CookbookDTO> createResource(Cookbook cookbook) {
		var resource = new Resource<CookbookDTO>(cookbookMapper.map(cookbook));
		return cookbookResourceProcessor.process(resource);
	}

	private Resource<RecipeDTO> createResource(Long cookbookId, Recipe recipe) {
		return map(cookbookId, recipeMapper.map(recipe));
	}

	private Resource<RecipeDTO> map(Long cookbookId, RecipeDTO recipe) {
		var resource = new Resource<>(recipe);
		resource.add(entityLinks.linkForSingleResource(CookbookDTO.class, cookbookId).slash("recipes").slash(recipe.getDatabaseId()).withSelfRel());
		return resource;
	}
}
