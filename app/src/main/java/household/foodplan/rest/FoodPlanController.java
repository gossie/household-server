package household.foodplan.rest;

import household.foodplan.domain.FoodPlan;
import household.foodplan.domain.FoodPlanService;
import household.foodplan.domain.Recipe;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/foodPlans")
@CrossOrigin
@RequiredArgsConstructor
public class FoodPlanController {

    private final FoodPlanDTOMapper foodPlanMapper;
    private final MealDTOMapper mealMapper;
    private final FoodPlanService foodPlanService;

    @GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
    public FoodPlanDTO getFoodPlan(@PathVariable String id) {
        return addLinks(createResource(foodPlanService.getFoodPlan(id)));
    }

    @DeleteMapping(path="/{id}/meals", produces={"application/vnd.household.v1+json"})
    public FoodPlanDTO clear(@PathVariable String id) {
        return addLinks(createResource(foodPlanService.clear(id)));
    }

    @PutMapping(path="/{id}/meals/{mealId}", produces={"application/vnd.household.v1+json"}, consumes={"application/vnd.household.v1+json"})
    public FoodPlanDTO updateMeal(@PathVariable String id, @PathVariable String mealId, @RequestBody ChangeMealRequest request) {
        return addLinks(createResource(foodPlanService.updateMeal(id, mealId, createRecipe(request) , mealMapper.map(request.getMeal()))));
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

    private FoodPlanDTO addLinks(FoodPlanDTO foodPlan) {
        return addFoodPlanSelfLink(clearFoodPlanLink(foodPlan)).getMeals().values().stream()
            .map(meal -> addMealSelfLink(foodPlan.getDatabaseId(), meal))
            .map(this::addRecipeLink)
            .reduce(foodPlan, (fp, m) -> fp, (m, fp) -> fp);
    }

    private FoodPlanDTO addFoodPlanSelfLink(FoodPlanDTO foodPlan) {
        return (FoodPlanDTO) foodPlan.add(Link.of("/api/foodPlans/" + foodPlan.getDatabaseId()));
    }

    private MealDTO addMealSelfLink(String foodPlanId, MealDTO meal) {
        return (MealDTO) meal.add(Link.of("/api/foodPlans/" + foodPlanId + "/meals/" + meal.getDatabaseId()));
    }

    private MealDTO addRecipeLink(MealDTO meal) {
        return meal.getCookbookId()
            .flatMap(cookbookId -> meal.getRecipeId())
            .map(recipeId ->  meal.add(Link.of("/api/cookbooks/" + meal.getCookbookId().get() + "/recipes/" + recipeId, "recipe")))
            .map(MealDTO.class::cast)
            .orElse(meal);
    }

    private FoodPlanDTO clearFoodPlanLink(FoodPlanDTO foodPlan) {
        return (FoodPlanDTO) foodPlan.add(Link.of("/api/foodPlans/" + foodPlan.getDatabaseId() + "/meals", "clear"));
    }
}
