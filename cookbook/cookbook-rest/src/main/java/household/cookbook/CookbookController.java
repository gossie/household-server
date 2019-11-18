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
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/cookbooks")
@CrossOrigin
@RequiredArgsConstructor
public class CookbookController {

	private final CookbookDTOMapper cookbookMapper;
	private final RecipeDTOMapper recipeMapper;
	private final CookbookService cookbookService;

    @PostMapping(produces = {"application/vnd.household.v1+json"})
    public Mono<CookbookDTO> createCookbook() {
        return Mono.just(createResource(cookbookService.createCookbook()))
            .flatMap(this::addLinks);
    }

	@GetMapping(path="/{cookbookId}", produces={"application/vnd.household.min.v1+json"})
	public Mono<CookbookDTO> getMinifiedRecipes(@PathVariable Long cookbookId) {
		return Mono.just(createResource(cookbookService.getMinifiedCookbook(cookbookId)))
            .flatMap(this::addLinks);
	}

	@GetMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.v1+json"})
	public Mono<RecipeDTO> getRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		return Mono.just(createResource(cookbookService.getRecipe(cookbookId, recipeId)))
            .flatMap(recipe -> addRecipeSelfLink(cookbookId, recipe));
	}

	@PutMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.min.v1+json"}, consumes={"application/vnd.household.v1+json"})
	public Mono<CookbookDTO> editRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId, @RequestBody RecipeDTO recipe) {
		return Mono.just(createResource(cookbookService.editRecipe(cookbookId, recipeId, recipeMapper.map(recipe))))
            .flatMap(this::addLinks);
	}

    @DeleteMapping(path="/{cookbookId}/recipes/{recipeId}", produces={"application/vnd.household.min.v1+json"})
	public Mono<CookbookDTO> deleteRecipe(@PathVariable Long cookbookId, @PathVariable Long recipeId) {
		return Mono.just(createResource(cookbookService.deleteRecipe(cookbookId, recipeId)))
            .flatMap(this::addLinks);
	}

	@PostMapping(path="/{cookbookId}/recipes", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.min.v1+json"})
	public Mono<CookbookDTO> addRecipe(@PathVariable Long cookbookId, @RequestBody RecipeDTO recipe) {
		return Mono.just(createResource(cookbookService.addRecipe(cookbookId, recipeMapper.map(recipe))))
            .flatMap(this::addLinks);
	}

	private CookbookDTO createResource(Cookbook cookbook) {
		return cookbookMapper.map(cookbook);
	}

	private RecipeDTO createResource(Recipe recipe) {
		return recipeMapper.map(recipe);
	}

    private Mono<CookbookDTO> addLinks(CookbookDTO cookbook) {
         return addCookbookSelfLink(cookbook)
             .flatMap(this::createRecipeLink)
             .flatMapIterable(CookbookDTO::getRecipes)
             .flatMap(recipe -> addRecipeSelfLink(cookbook.getDatabaseId(), recipe))
             .collect(() -> cookbook, (a, b) -> {});
    }

    private Mono<RecipeDTO> addRecipeSelfLink(Long cookbookId, RecipeDTO recipe) {
        return linkTo(methodOn(CookbookController.class).getRecipe(cookbookId, recipe.getDatabaseId()))
            .withSelfRel()
            .toMono()
            .map(recipe::add)
            .map(RecipeDTO.class::cast);
    }

    private Mono<CookbookDTO> addCookbookSelfLink(CookbookDTO cookbook) {
        return linkTo(methodOn(CookbookController.class).getMinifiedRecipes(cookbook.getDatabaseId()))
            .withSelfRel()
            .toMono()
            .map(cookbook::add)
            .map(CookbookDTO.class::cast);
    }

    private Mono<CookbookDTO> createRecipeLink(CookbookDTO cookbook) {
        return linkTo(methodOn(CookbookController.class).addRecipe(cookbook.getDatabaseId(), null))
            .withRel("create")
            .toMono()
            .map(cookbook::add)
            .map(CookbookDTO.class::cast);
    }
}
