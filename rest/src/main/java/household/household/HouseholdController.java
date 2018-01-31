package household.household;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	
	@PostMapping(produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<HouseholdDTO>> createHousehold() throws InterruptedException, ExecutionException {
	    ExecutorService threadPool = Executors.newFixedThreadPool(4);
	    Future<ShoppingList> shoppingList = threadPool.submit(shoppingListService::createShoppingList);
	    Future<CleaningPlan> cleaningPlan = threadPool.submit(cleaningPlanService::createCleaningPlan);
	    Future<FoodPlan> foodPlan = threadPool.submit(foodPlanService::createFoodPlan);
	    Future<Cookbook> cookbook = threadPool.submit(cookbookService::createCookbook);

		Household household;
        try {
            household = householdService.createHousehold(shoppingList.get(), cleaningPlan.get(), foodPlan.get(), cookbook.get());
        } finally {
            threadPool.shutdown();
        }
		User currentUser = userService.determineCurrentUser();
		currentUser.setHouseholdId(household.getId());
		userService.updateUser(currentUser);
		
		return ResponseEntity.ok(createResource(household));
	}
	
	@GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<HouseholdDTO>> getHoushold(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(householdService.getHousehold(id)));
	}
	
	private Resource<HouseholdDTO> createResource(Household household) {
		HouseholdDTO housholdDTO = householdMapper.map(household);
		userService.determineUsers(household.getId()).stream()
                .map(user -> new ParticipantDTO(user.getId(), user.getEmail()))
                .forEach(housholdDTO::addParticipant);
		
        Resource<HouseholdDTO> resource = new Resource<HouseholdDTO>(housholdDTO);
		return householdResourceProcessor.process(resource);
	}
}
