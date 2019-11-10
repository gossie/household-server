package household.household;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

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
	public Mono<HouseholdDTO> createHousehold() {
		ShoppingList shoppingList = shoppingListService.createShoppingList();
		CleaningPlan cleaningPlan = cleaningPlanService.createCleaningPlan();
		FoodPlan foodPlan = foodPlanService.createFoodPlan();
		Cookbook cookbook = cookbookService.createCookbook();

		Household household = householdService.createHousehold(shoppingList.getId(), cleaningPlan.getId(), foodPlan.getId(), cookbook.getId());
		return Mono.from(userService.determineCurrentUser())
            .map(currentUser -> {
                currentUser.setHouseholdId(household.getId());
                userService.updateUser(currentUser);
                return household;
            })
            .map(this::createResource)
            .flatMap(this::addLinks);
	}

	@GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
	public Mono<HouseholdDTO> getHousehold(@PathVariable Long id) {
		return Mono.just(createResource(householdService.getHousehold(id)))
            .flatMap(this::addLinks);
	}

	private HouseholdDTO createResource(Household household) {
		HouseholdDTO householdDTO = householdMapper.map(household);
		userService.determineUsers(household.getId())
                .stream()
                .map(user -> new ParticipantDTO(user.getId(), user.getEmail()))
                .forEach(householdDTO::addParticipant);

		return householdDTO;
	}

    private Mono<HouseholdDTO> addLinks(HouseholdDTO household) {
        return addSelfLink(household)
            .map(this::addShoppingListLink)
            .map(this::addCleaningPlanLink)
            .map(this::addFoodPlanLink)
            .map(this::addCookbookLink);
    }

    private Mono<HouseholdDTO> addSelfLink(HouseholdDTO household) {
        return linkTo(methodOn(HouseholdController.class).getHousehold(household.getDatabaseId()))
            .withSelfRel()
            .toMono()
            .map(household::add)
            .map(HouseholdDTO.class::cast);
    }

    private HouseholdDTO addShoppingListLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(new Link("/api/shoppingLists/" + household.getShoppingListId(), "shoppingList"));
    }

    private HouseholdDTO addCleaningPlanLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(new Link("/api/cleaningPlans/" + household.getCleaningPlanId(), "cleaningPlan"));
    }

    private HouseholdDTO addFoodPlanLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(new Link("/api/foodPlans/" + household.getFoodPlanId(), "foodPlan"));
    }

    private HouseholdDTO addCookbookLink(HouseholdDTO household) {
        return (HouseholdDTO) household.add(new Link("/api/cookbooks/" + household.getCookbookId(), "cookbook"));
    }
}
