package household.household;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import household.cleaningplan.CleaningPlan;
import household.cleaningplan.CleaningPlanService;
import household.cookbook.Cookbook;
import household.cookbook.CookbookService;
import household.foodplan.FoodPlan;
import household.foodplan.FoodPlanService;
import household.shoppinglist.ShoppingList;
import household.shoppinglist.ShoppingListService;
import household.user.User;
import household.user.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/households")
@ExposesResourceFor(HouseholdDTO.class)
@CrossOrigin
@RequiredArgsConstructor
public class HouseholdController {

	private final UserService userService;
	private final ShoppingListService shoppingListService;
	private final CleaningPlanService cleaningPlanService;
	private final FoodPlanService foodPlanService;
	private final CookbookService cookbookService;

	private final HouseholdService householdService;
	private final HouseholdDTOMapper householdMapper;
	private final HouseholdResourceProcessor householdResourceProcessor;

	@PostMapping(produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<HouseholdDTO>> createHousehold() {
		ShoppingList shoppingList = shoppingListService.createShoppingList();
		CleaningPlan cleaningPlan = cleaningPlanService.createCleaningPlan();
		FoodPlan foodPlan = foodPlanService.createFoodPlan();
		Cookbook cookbook = cookbookService.createCookbook();

		Household household = householdService.createHousehold(shoppingList.getId(), cleaningPlan.getId(), foodPlan.getId(), cookbook.getId());
		User currentUser = userService.determineCurrentUser();
		currentUser.setHouseholdId(household.getId());
		userService.updateUser(currentUser);

		return ResponseEntity.ok(createResource(household));
	}

	@DeleteMapping(path="/{id}")
    @ResponseStatus(value = HttpStatus.OK)
	public void deleteHousehold(@PathVariable Long id) {
	    householdService.deleteHousehold(id);
    }

	@GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<HouseholdDTO>> getHoushold(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(householdService.getHousehold(id)));
	}

	private Resource<HouseholdDTO> createResource(Household household) {
		HouseholdDTO housholdDTO = householdMapper.map(household);
		userService.determineUsers(household.getId())
                .stream()
                .map(user -> new ParticipantDTO(user.getId(), user.getEmail()))
                .forEach(housholdDTO::addParticipant);

        Resource<HouseholdDTO> resource = new Resource<HouseholdDTO>(housholdDTO);
		return householdResourceProcessor.process(resource);
	}
}
