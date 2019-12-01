package household.household;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/households")
@CrossOrigin
@RequiredArgsConstructor
public class HouseholdController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseholdController.class);

    private static final MediaType CUSTOM_TYPE = new CustomMediaType();
    private static final Comparator<Result> RESULT_COMPARATOR = new ResultComparator();

    private final WebClient webClient;
	private final HouseholdService householdService;
	private final HouseholdDTOMapper householdMapper;

	@Value("${shopping-list.url}")
    private String shoppingListUrl;
    @Value("${cleaning-plan.url}")
    private String cleaningPlanUrl;
    @Value("${food-plan.url}")
    private String foodPlanUrl;
    @Value("${cookbook.url}")
    private String cookbookUrl;

    @PostMapping(produces={"application/vnd.household.v1+json"})
    public Mono<HouseholdDTO> createHousehold(ServerWebExchange exchange) {
        return Flux.concat(postRequest(shoppingListUrl + "/api/shoppingLists", null), postRequest(cleaningPlanUrl + "/api/cleaningPlans", exchange), postRequest(foodPlanUrl + "/api/foodPlans", exchange), postRequest(cookbookUrl + "/api/cookbooks", exchange))
            .parallel()
            .runOn(Schedulers.parallel())
            .collectSortedList(RESULT_COMPARATOR)
            .map(this::determineAllDatabaseIds)
            .map(list -> householdService.createHousehold(list.get(0), list.get(1), list.get(2), list.get(3)))
            .map(this::createResource)
            .flatMap(this::addLinks);
    }

    private List<Long> determineAllDatabaseIds(List<Result> result) {
        LOGGER.debug("determine id of {}", result);
        return result.stream()
            .map(r -> r.getLink("self"))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Link::getHref)
            .map(href -> href.split("/"))
            .map(a -> a[a.length - 1])
            .map(Long::valueOf)
            .collect(Collectors.toList());
    }

    private Long determineDatabaseId(Result result) {
        LOGGER.debug("determine id of {}", result);
        return result.getLink("self")
            .map(Link::getHref)
            .map(href -> href.split("/"))
            .map(a -> a[a.length - 1])
            .map(Long::valueOf)
            .orElse(null);
    }

    private Mono<Result> postRequest(String url, ServerWebExchange exchange) {
        return webClient
            .post()
            .uri(url)
            .accept(CUSTOM_TYPE)
            .retrieve()
            .bodyToMono(Result.class);
    }

	@GetMapping(path="/{id}", produces={"application/vnd.household.v1+json"})
	public Mono<HouseholdDTO> getHousehold(@PathVariable Long id) {
		return Mono.just(createResource(householdService.getHousehold(id)))
            .flatMap(this::addLinks);
	}

	private HouseholdDTO createResource(Household household) {
		HouseholdDTO householdDTO = householdMapper.map(household);
		// TODO: do user stuff
		/*
		userService.determineUsers(household.getId())
                .stream()
                .map(user -> new ParticipantDTO(user.getId(), user.getEmail()))
                .forEach(householdDTO::addParticipant);
        */
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
