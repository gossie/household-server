package household.foodplan;

import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

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
                    value.add(new Link("/api/cookbooks/" + cookbookId + "/recipes/" + recipeId, "recipe"));
                });
            });
        });

        return resource;
    }

}
