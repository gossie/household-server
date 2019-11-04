package household.household;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class HouseholdResourceProcessor implements ResourceProcessor<Resource<HouseholdDTO>> {

	private final EntityLinks entityLinks;

	@Override
	public Resource<HouseholdDTO> process(Resource<HouseholdDTO> resource) {
		HouseholdDTO household = resource.getContent();

		resource.add(entityLinks.linkForSingleResource(HouseholdDTO.class, household.getDatabaseId()).withSelfRel());

        resource.add(new Link("/api/shoppingLists/" + household.getShoppingListId(), "shoppingList"));
        resource.add(new Link("/api/cleaningPlans/" + household.getCleaningPlanId(), "cleaningPlan"));
        resource.add(new Link("/api/foodPlans/" + household.getFoodPlanId(), "foodPlan"));
        resource.add(new Link("/api/cookbooks/" + household.getCookbookId(), "cookbook"));

		return resource;
	}
}
