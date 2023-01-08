package household.household.rest;

import java.security.Principal;

import household.cleaningplan.domain.CleaningPlan;
import household.cleaningplan.domain.CleaningPlanService;
import household.cookbook.domain.Cookbook;
import household.cookbook.domain.CookbookService;
import household.foodplan.domain.FoodPlan;
import household.foodplan.domain.FoodPlanService;
import household.household.domain.Household;
import household.household.domain.HouseholdService;
import household.shoppinglist.domain.ShoppingList;
import household.shoppinglist.domain.ShoppingListService;
import household.user.domain.UserService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/households")
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

	@PostMapping(produces={"application/vnd.household.v1+json"})
	public ResponseEntity<HouseholdDTO> createHousehold(Principal principal) {
		ShoppingList shoppingList = shoppingListService.createShoppingList();
		CleaningPlan cleaningPlan = cleaningPlanService.createCleaningPlan();
		FoodPlan foodPlan = foodPlanService.createFoodPlan();
		Cookbook cookbook = cookbookService.createCookbook();

		Household household = householdService.createHousehold(shoppingList.getId(), cleaningPlan.getId(), foodPlan.getId(), cookbook.getId());
		return userService.determineUserByEmail(principal.getName())
            .map(currentUser -> {
                currentUser.setHouseholdId(household.getId());
                userService.updateUser(currentUser);
                return household;
            })
            .map(this::createResource)
            .map(this::addLinks)
            .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
            .orElseThrow();
	}

	@GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
	public HouseholdDTO getHousehold(@PathVariable String id) {
		return addLinks(createResource(householdService.getHousehold(id)));
	}

	private HouseholdDTO createResource(Household household) {
		HouseholdDTO householdDTO = householdMapper.map(household);
		userService.determineUsers(household.getId())
                .stream()
                .map(user -> new ParticipantDTO(user.getId(), user.getEmail()))
                .forEach(householdDTO::addParticipant);

		return householdDTO;
	}

    private HouseholdDTO addLinks(HouseholdDTO household) {
        return addSelfLink(addShoppingListLink(addCleaningPlanLink(addFoodPlanLink(addCookbookLink(household)))));
    }

    private HouseholdDTO addSelfLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(Link.of("/api/shoppingLists/" + household.getShoppingListId()));
    }

    private HouseholdDTO addShoppingListLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(Link.of("/api/shoppingLists/" + household.getShoppingListId(), "shoppingList"));
    }

    private HouseholdDTO addCleaningPlanLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(Link.of("/api/cleaningPlans/" + household.getCleaningPlanId(), "cleaningPlan"));
    }

    private HouseholdDTO addFoodPlanLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(Link.of("/api/foodPlans/" + household.getFoodPlanId(), "foodPlan"));
    }

    private HouseholdDTO addCookbookLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(Link.of("/api/cookbooks/" + household.getCookbookId(), "cookbook"));
    }
}
