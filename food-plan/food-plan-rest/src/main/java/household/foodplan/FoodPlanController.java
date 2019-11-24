package household.foodplan;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/foodPlans")
@CrossOrigin
@RequiredArgsConstructor
public class FoodPlanController {

    private final FoodPlanDTOMapper foodPlanMapper;
    private final MealDTOMapper mealMapper;
    private final FoodPlanService foodPlanService;

    @PostMapping(produces = {"application/vnd.household.v1+json"})
    public Mono<FoodPlanDTO> createFoodPlan() {
        return Mono.just(createResource(foodPlanService.createFoodPlan()))
            .flatMap(this::addLinks);
    }

    @GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
    public Mono<FoodPlanDTO> getFoodPlan(@PathVariable Long id) {
        return Mono.just(createResource(foodPlanService.getFoodPlan(id)))
            .flatMap(this::addLinks);
    }

    @DeleteMapping(path="/{id}/meals", produces={"application/vnd.household.v1+json"})
    public Mono<FoodPlanDTO> clear(@PathVariable Long id) {
        return Mono.just(createResource(foodPlanService.clear(id)))
            .flatMap(this::addLinks);
    }

    @PutMapping(path="/{id}/meals/{mealId}", produces={"application/vnd.household.v1+json"}, consumes={"application/vnd.household.v1+json"})
    public Mono<FoodPlanDTO> updateMeal(@PathVariable Long id, @PathVariable Long mealId, @RequestBody ChangeMealRequest request) {
        return Mono.just(createResource(foodPlanService.updateMeal(id, mealId, createRecipe(request) , mealMapper.map(request.getMeal()))))
            .flatMap(this::addLinks);
    }

    private Recipe createRecipe(ChangeMealRequest request) {
        if (request.getCookbookId() != null && request.getRecipeId() != null) {
            return new Recipe(request.getRecipeId(), request.getCookbookId());
        }
        return null;
    }

    private FoodPlanDTO createResource(FoodPlan foodPlan) {
        return foodPlanMapper.map(foodPlan);
    }

    private Mono<FoodPlanDTO> addLinks(FoodPlanDTO foodPlan) {
        return addFoodPlanSelfLink(foodPlan)
            .flatMap(this::clearFoodPlanLink)
            .flatMapIterable(fp -> fp.getMeals().values())
            .flatMap(meal -> addMealSelfLink(foodPlan.getDatabaseId(), meal))
            .map(this::addRecipeLink)
            .collect(() -> foodPlan, (a, b) -> {});
    }

    private Mono<FoodPlanDTO> addFoodPlanSelfLink(FoodPlanDTO foodPlan) {
        return linkTo(methodOn(FoodPlanController.class).getFoodPlan(foodPlan.getDatabaseId()))
            .withSelfRel()
            .toMono()
            .map(foodPlan::add)
            .map(FoodPlanDTO.class::cast);
    }

    private Mono<MealDTO> addMealSelfLink(Long foodPlanId, MealDTO meal) {
        return linkTo(methodOn(FoodPlanController.class).updateMeal(foodPlanId, meal.getDatabaseId(), null))
            .withSelfRel()
            .toMono()
            .map(meal::add)
            .map(MealDTO.class::cast);
    }

    private MealDTO addRecipeLink(MealDTO meal) {
        return meal.getCookbookId()
            .flatMap(cookbookId -> meal.getRecipeId())
            .map(recipeId ->  meal.add(new Link("/api/cookbooks/" + meal.getCookbookId().get() + "/recipes/" + recipeId, "recipe")))
            .map(MealDTO.class::cast)
            .orElse(meal);
    }

    private Mono<FoodPlanDTO> clearFoodPlanLink(FoodPlanDTO foodPlan) {
        return linkTo(methodOn(FoodPlanController.class).clear(foodPlan.getDatabaseId()))
            .withRel("clear")
            .toMono()
            .map(foodPlan::add)
            .map(FoodPlanDTO.class::cast);
    }
}
