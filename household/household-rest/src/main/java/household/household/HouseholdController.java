package household.household;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import household.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/households")
@CrossOrigin
@RequiredArgsConstructor
public class HouseholdController {

    private static final MediaType CUSTOM_TYPE = new CustomMediaType();

    private final WebClient webClient;
	private final UserService userService;
	private final HouseholdService householdService;
	private final HouseholdDTOMapper householdMapper;

	@PostMapping(produces={"application/vnd.household.v1+json"})
	public Mono<HouseholdDTO> createHousehold(ServerWebExchange exchange) {
        return Flux.concat(postRequest("/api/shoppingLists", exchange), postRequest("/api/cleaningPlans", exchange), postRequest("/api/foodPlans", exchange), postRequest("/api/cookbooks", exchange))
            .collectList()
            .map(list -> householdService.createHousehold(list.get(0).getDatabaseId(), list.get(1).getDatabaseId(), list.get(2).getDatabaseId(), list.get(3).getDatabaseId()))
            .flatMap(this::handleUser)
            .map(this::createResource)
            .flatMap(this::addLinks);
	}

	private Mono<AbstractDTO> postRequest(String url, ServerWebExchange exchange) {
        HttpCookie xsrfToken = exchange.getRequest().getCookies().getFirst("XSRF-TOKEN");
        HttpCookie sessionId = exchange.getRequest().getCookies().getFirst("JSESSIONID");

	    return webClient
            .post()
            .uri(url)
            .header("XSRF-TOKEN", xsrfToken.getValue())
            .cookie("JSESSIONID", sessionId.getValue())
            .accept(CUSTOM_TYPE)
            .retrieve()
            .bodyToMono(AbstractDTO.class);
    }

    private Mono<Household> handleUser(Household household) {
        return Mono.from(userService.determineCurrentUser())
            .map(currentUser -> {
                currentUser.setHouseholdId(household.getId());
                userService.updateUser(currentUser);
                return household;
            });
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(value = HttpStatus.OK)
	public void deleteHousehold(@PathVariable Long id) {
	    householdService.deleteHousehold(id);
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

    private static class CustomMediaType extends MediaType {

        public CustomMediaType() {
            super("application", "vnd.household.v1+json");
        }
    }
}
