package household.household;

import static household.Constants.DEFAULT_MEDIA_TYPE;
import static household.Constants.V1_MEDIA_TYPE;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/households")
@ExposesResourceFor(HouseholdDTO.class)
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
	
	@PostMapping(produces={V1_MEDIA_TYPE, DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<HouseholdDTO>> createHousehold() {
		ShoppingList shoppingList = shoppingListService.createShoppingList();
		CleaningPlan cleaningPlan = cleaningPlanService.createCleaningPlan();
		FoodPlan foodPlan = foodPlanService.createFoodPlan();
		Cookbook cookbook = cookbookService.createCookbook();

		Household household = householdService.createHousehold(shoppingList, cleaningPlan, foodPlan, cookbook);
		User currentUser = userService.determineCurrentUser();
		currentUser.setHouseholdId(household.getId());
		userService.updateUser(currentUser);
		
		return ResponseEntity.ok(createResource(household));
	}
	
	@GetMapping(path="/{id}", produces={V1_MEDIA_TYPE, DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<HouseholdDTO>> getHoushold(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(householdService.getHousehold(id)));
	}
	
	private Resource<HouseholdDTO> createResource(Household household) {
		Resource<HouseholdDTO> resource = new Resource<HouseholdDTO>(householdMapper.map(household));
		return householdResourceProcessor.process(resource);
	}
}
