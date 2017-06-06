package household.household;

import static household.Constants.DEFAULT_MEDIA_TYPE;

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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/households")
@ExposesResourceFor(HouseholdDTO.class)
@RequiredArgsConstructor
public class HouseholdController {

	private final ShoppingListService shoppingListService;
	private final CleaningPlanService cleaningPlanService;
	private final FoodPlanService foodPlanService;
	private final CookbookService cookbookService;
	
	private final HouseholdService householdService;
	private final HouseholdDTOMapper householdMapper;
	private final HouseholdResourceProcessor householdResourceProcessor;
	
	@PostMapping(produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<HouseholdDTO>> createHousehold() {
		ShoppingList shoppingList = shoppingListService.createShoppingList();
		CleaningPlan cleaningPlan = cleaningPlanService.createCleaningPlan();
		FoodPlan foodPlan = foodPlanService.createFoodPlan();
		Cookbook cookbook = cookbookService.createCookbook();
		
		return ResponseEntity.ok(createResource(householdService.createHousehold(shoppingList, cleaningPlan, foodPlan, cookbook)));
	}
	
	@GetMapping(path="/{id}", produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<HouseholdDTO>> getHoushold(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(householdService.getHousehold(id)));
	}
	
	private Resource<HouseholdDTO> createResource(Household household) {
		Resource<HouseholdDTO> resource = new Resource<HouseholdDTO>(householdMapper.map(household));
		return householdResourceProcessor.process(resource);
	}
}
