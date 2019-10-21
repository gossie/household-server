package household.foodplan;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import household.cookbook.CookbookDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FoodPlanResourceProcessor implements ResourceProcessor<Resource<FoodPlanDTO>> {

    private final EntityLinks entityLinks;

    @Override
    public Resource<FoodPlanDTO> process(Resource<FoodPlanDTO> resource) {
        FoodPlanDTO foodPlanDTO = resource.getContent();
        resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).withSelfRel());
        resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).withRel("save"));
        resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).slash("/meals").withRel("clear"));

        foodPlanDTO.getMeals().forEach((key, value) -> {
            value.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, foodPlanDTO.getDatabaseId()).slash("/meals").slash(value.getDatabaseId()).withSelfRel());
            value.getCookbookId().ifPresent(cookbookId -> {
                value.getRecipeId().ifPresent(recipeId -> {
                    value.add(entityLinks.linkForSingleResource(CookbookDTO.class, cookbookId).slash("/recipes").slash(recipeId).withRel("recipe"));
                });
            });
        });

        return resource;
    }

}
