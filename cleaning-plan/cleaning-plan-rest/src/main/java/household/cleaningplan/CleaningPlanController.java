package household.cleaningplan;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/cleaningPlans")
@CrossOrigin
@RequiredArgsConstructor
public class CleaningPlanController {

	private final CleaningPlanDTOMapper cleaningPlanMapper;
	private final ChoreDTOMapper choreMapper;
	private final CleaningPlanService cleaningPlanService;

	@GetMapping(path="/{cleaningPlanId}", produces={"application/vnd.household.v1+json"})
	public Mono<CleaningPlanDTO> getCleaningPlan(@PathVariable Long cleaningPlanId) {
		return Mono.just(cleaningPlanMapper.map(cleaningPlanService.getCleaningPlan(cleaningPlanId)))
            .flatMap(this::addLinks);
	}

    @PatchMapping(path="/{cleaningPlanId}/chores/{choreId}", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
	public Mono<CleaningPlanDTO> updateChore(@PathVariable Long cleaningPlanId, @PathVariable Long choreId, @RequestBody ChoreDTO chore) {
		return Mono.just(cleaningPlanMapper.map(cleaningPlanService.update(cleaningPlanId, choreMapper.map(choreId, chore))))
            .flatMap(this::addLinks);
	}

	@PostMapping(path="/{cleaningPlanId}/chores", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
	public Mono<CleaningPlanDTO> addChore(@PathVariable Long cleaningPlanId, @RequestBody ChoreDTO chore) {
		return Mono.just(cleaningPlanMapper.map(cleaningPlanService.addChore(cleaningPlanId, choreMapper.map(chore))))
            .flatMap(this::addLinks);
	}

	@DeleteMapping(path="/{cleaningPlanId}/chores/{choreId}", produces={"application/vnd.household.v1+json"})
	public Mono<CleaningPlanDTO> removeChore(@PathVariable Long cleaningPlanId, @PathVariable Long choreId) {
		return Mono.just(cleaningPlanMapper.map(cleaningPlanService.removeChore(cleaningPlanId, choreId)))
            .flatMap(this::addLinks);
	}

    private Mono<CleaningPlanDTO> addLinks(CleaningPlanDTO cleaningPlan) {
	    return addSelfLink(cleaningPlan)
            .flatMap(this::addAddChoreLink)
            .flatMapIterable(CleaningPlanDTO::getChores)
            .flatMap(chore -> this.addSelectChoreLink(cleaningPlan.getDatabaseId(), chore))
            .collect(() -> cleaningPlan, (a, b) -> {});
    }

    private Mono<CleaningPlanDTO> addSelfLink(CleaningPlanDTO cleaningPlan) {
        return linkTo(methodOn(CleaningPlanController.class).getCleaningPlan(cleaningPlan.getDatabaseId()))
            .withSelfRel()
            .toMono()
            .map(cleaningPlan::add)
            .map(CleaningPlanDTO.class::cast);
    }

    private Mono<CleaningPlanDTO> addAddChoreLink(CleaningPlanDTO cleaningPlan) {
        return linkTo(methodOn(CleaningPlanController.class).addChore(cleaningPlan.getDatabaseId(), null))
            .withRel("add")
            .toMono()
            .map(cleaningPlan::add)
            .map(CleaningPlanDTO.class::cast);
    }

    private Mono<ChoreDTO> addSelectChoreLink(Long cleaningPlanId, ChoreDTO chore) {
        return linkTo(methodOn(CleaningPlanController.class).updateChore(cleaningPlanId, chore.getDatabaseId(), null))
            .withRel("select")
            .toMono()
            .map(chore::add)
            .map(ChoreDTO.class::cast);
    }
}
