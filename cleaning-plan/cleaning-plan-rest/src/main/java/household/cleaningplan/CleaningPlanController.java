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
    private final TaskDTOMapper taskMapper;
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

    @PatchMapping(path="/{cleaningPlanId}/tasks/{taskId}", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public Mono<CleaningPlanDTO> updateTask(@PathVariable Long cleaningPlanId, @PathVariable Long taskId, @RequestBody TaskDTO task) {
        return Mono.just(cleaningPlanMapper.map(cleaningPlanService.update(cleaningPlanId, taskMapper.map(taskId, task))))
            .flatMap(this::addLinks);
    }

    @PostMapping(path="/{cleaningPlanId}/chores", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public Mono<CleaningPlanDTO> addChore(@PathVariable Long cleaningPlanId, @RequestBody ChoreDTO chore) {
        return Mono.just(cleaningPlanMapper.map(cleaningPlanService.addChore(cleaningPlanId, choreMapper.map(chore))))
            .flatMap(this::addLinks);
    }

    @PostMapping(path="/{cleaningPlanId}/tasks", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public Mono<CleaningPlanDTO> addTask(@PathVariable Long cleaningPlanId, @RequestBody TaskDTO task) {
        return Mono.just(cleaningPlanMapper.map(cleaningPlanService.addTask(cleaningPlanId, taskMapper.map(task))))
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
            .flatMap(this::addAddTaskLink)
            .flatMapIterable(CleaningPlanDTO::getChores)
            .flatMap(chore -> this.addSelectChoreLink(cleaningPlan.getDatabaseId(), chore))
            .flatMap(chore -> this.addDeleteChoreLink(cleaningPlan.getDatabaseId(), chore))
            .flatMap(chore -> this.addSaveChoreLink(cleaningPlan.getDatabaseId(), chore))
            .flatMapIterable(c -> cleaningPlan.getTasks())
            .flatMap(task -> this.addSelectTaskLink(cleaningPlan.getDatabaseId(), task))
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
            .withRel("addChore")
            .toMono()
            .map(cleaningPlan::add)
            .map(CleaningPlanDTO.class::cast);
    }

    private Mono<CleaningPlanDTO> addAddTaskLink(CleaningPlanDTO cleaningPlan) {
        return linkTo(methodOn(CleaningPlanController.class).addTask(cleaningPlan.getDatabaseId(), null))
            .withRel("addTask")
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

    private Mono<ChoreDTO> addDeleteChoreLink(Long cleaningPlanId, ChoreDTO chore) {
        return linkTo(methodOn(CleaningPlanController.class).removeChore(cleaningPlanId, chore.getDatabaseId()))
            .withRel("delete")
            .toMono()
            .map(chore::add)
            .map(ChoreDTO.class::cast);
    }

    private Mono<ChoreDTO> addSaveChoreLink(Long cleaningPlanId, ChoreDTO chore) {
        return linkTo(methodOn(CleaningPlanController.class).updateChore(cleaningPlanId, chore.getDatabaseId(), null))
            .withRel("save")
            .toMono()
            .map(chore::add)
            .map(ChoreDTO.class::cast);
    }

    private Mono<TaskDTO> addSelectTaskLink(Long cleaningPlanId, TaskDTO task) {
        return linkTo(methodOn(CleaningPlanController.class).updateTask(cleaningPlanId, task.getDatabaseId(), null))
            .withRel("select")
            .toMono()
            .map(task::add)
            .map(TaskDTO.class::cast);
    }
}
