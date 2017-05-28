package household.cookbook;

import static household.Constants.DEFAULT_MEDIA_TYPE;
import static household.Constants.MIN_MEDIA_TYPE;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class CookbookController {
	
	private final CookbookMapper cookbookMapper;
	private final RecipeMapper recipeMapper;
	private final CookbookService cookbookService;
	private final CookbookResourceProcessor cookbookResourceProcessor;
	private final EntityLinks entityLinks;

	@GetMapping(path="/{cookbookId}", produces={MIN_MEDIA_TYPE})
	public HttpEntity<Resource<CookbookDTO>> getMinifiedRecipes(@PathVariable Long cookbookId) {
		CookbookEntity minifiedCookbook = cookbookService.getMinifiedCookbook(cookbookId);
		return ResponseEntity.ok(createResource(minifiedCookbook));
	}
	
	@GetMapping(path="/{cookbookId}/recipes/{recipeId}", produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<RecipeDTO>> getRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		RecipeEntity recipe = cookbookService.getRecipe(cookbookId, recipeId);
		return ResponseEntity.ok(createResource(cookbookId, recipe));
	}
	
	@PutMapping(path="/{cookbookId}/recipes/{recipeId}", produces={DEFAULT_MEDIA_TYPE}, consumes={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<CookbookDTO>> editRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId, @RequestBody RecipeDTO recipe) {
		CookbookEntity cookbook = cookbookService.editRecipe(cookbookId, recipeId, recipeMapper.map(recipe));
		return ResponseEntity.ok(createResource(cookbook));
	}
	
	@PostMapping(path="/{cookbookId}/recipes", consumes={DEFAULT_MEDIA_TYPE}, produces={MIN_MEDIA_TYPE})
	public HttpEntity<Resource<CookbookDTO>> addRecipe(@PathVariable Long cookbookId, @RequestBody RecipeDTO recipe) {
		CookbookEntity minifiedCookbook = cookbookService.addRecipe(cookbookId, recipeMapper.map(recipe));
		return ResponseEntity.ok(createResource(minifiedCookbook));
	}
	
	private Resource<CookbookDTO> createResource(CookbookEntity cookbook) {
		Resource<CookbookDTO> resource = new Resource<CookbookDTO>(cookbookMapper.map(cookbook));
		return cookbookResourceProcessor.process(resource);
	}
	
	private Resource<RecipeDTO> createResource(Long cookbookId, RecipeEntity recipe) {
		return map(cookbookId, recipeMapper.map(recipe));
	}
	
	private Resource<RecipeDTO> map(Long cookbookId, RecipeDTO recipe) {
		Resource<RecipeDTO> resource = new Resource<>(recipe);
		resource.add(entityLinks.linkForSingleResource(CookbookDTO.class, cookbookId).slash("recipes").slash(recipe.getDatabaseId()).withSelfRel());
		return resource;
	}
}
