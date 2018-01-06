package household.household;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import household.cleaningplan.CleaningPlanDTO;
import household.cookbook.CookbookDTO;
import household.foodplan.FoodPlanDTO;
import household.shoppinglist.ShoppingListDTO;
import household.user.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HouseholdResourceProcessor implements ResourceProcessor<Resource<HouseholdDTO>> {

	private final EntityLinks entityLinks;
    private final UserService userService;

	@Override
	public Resource<HouseholdDTO> process(Resource<HouseholdDTO> resource) {
		HouseholdDTO household = resource.getContent();

        userService.determineUsers(household.getDatabaseId())
                .stream()
                .map(user -> new ParticipantDTO(user.getId(), user.getEmail()))
                .forEach(household::addParticipant);
                

		resource.add(entityLinks.linkForSingleResource(HouseholdDTO.class, household.getDatabaseId()).withSelfRel());

		resource.add(entityLinks.linkForSingleResource(ShoppingListDTO.class, household.getShoppingListId()).withRel("shoppingList"));
		resource.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, household.getCleaningPlanId()).withRel("cleaningPlan"));
		resource.add(entityLinks.linkForSingleResource(FoodPlanDTO.class, household.getFoodPlanId()).withRel("foodPlan"));
		resource.add(entityLinks.linkForSingleResource(CookbookDTO.class, household.getCookbookId()).withRel("cookbook"));

		return resource;
	}
}
