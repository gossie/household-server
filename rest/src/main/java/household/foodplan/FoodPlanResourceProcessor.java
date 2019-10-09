package household.foodplan;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import household.cookbook.RecipeDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FoodPlanResourceProcessor implements ResourceProcessor<Resource<FoodPlanDTO>> {

	private final EntityLinks entityLinks;
	private final FoodPlanRepository foodPlanRepository;

	@Override
	public Resource<FoodPlanDTO> process(Resource<FoodPlanDTO> resource) {
		FoodPlanDTO foodPlanDTO = resource.getContent();
        resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).withSelfRel());
        resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).withRel("save"));
		resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).slash("/meals").withRel("clear"));

		FoodPlan foodPlan = foodPlanRepository.determineFoodPlan(foodPlanDTO.getDatabaseId());
		foodPlan.getMeals().forEach((key, value) -> {
			value.getRecipe().ifPresent(r -> {
				foodPlanDTO.getMeals().get(key).add(entityLinks.linkForSingleResource(RecipeDTO.class, r.getId()).withRel("recipe"));
			});
		});

		return resource;
	}

}
