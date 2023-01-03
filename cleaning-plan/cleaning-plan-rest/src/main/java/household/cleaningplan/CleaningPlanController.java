package household.cleaningplan;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
	public CleaningPlanDTO getCleaningPlan(@PathVariable String cleaningPlanId) {
		return addLinks(cleaningPlanMapper.map(cleaningPlanService.getCleaningPlan(cleaningPlanId)));
	}

    @PutMapping(path="/{cleaningPlanId}/chores/{choreId}", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public CleaningPlanDTO updateChore(@PathVariable String cleaningPlanId, @PathVariable String choreId, @RequestBody ChoreDTO chore) {
        return addLinks(cleaningPlanMapper.map(cleaningPlanService.update(cleaningPlanId, choreMapper.map(choreId, chore))));
    }

    @PutMapping(path="/{cleaningPlanId}/tasks/{taskId}", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public CleaningPlanDTO updateTask(@PathVariable String cleaningPlanId, @PathVariable String taskId, @RequestBody TaskDTO task) {
        return addLinks(cleaningPlanMapper.map(cleaningPlanService.update(cleaningPlanId, taskMapper.map(taskId, task))));
    }

    @PostMapping(path="/{cleaningPlanId}/chores", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public CleaningPlanDTO addChore(@PathVariable String cleaningPlanId, @RequestBody ChoreDTO chore) {
        return addLinks(cleaningPlanMapper.map(cleaningPlanService.addChore(cleaningPlanId, choreMapper.map(chore))));
    }

    @PostMapping(path="/{cleaningPlanId}/tasks", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public CleaningPlanDTO addTask(@PathVariable String cleaningPlanId, @RequestBody TaskDTO task) {
        return addLinks(cleaningPlanMapper.map(cleaningPlanService.addTask(cleaningPlanId, taskMapper.map(task))));
    }

	@DeleteMapping(path="/{cleaningPlanId}/chores/{choreId}", produces={"application/vnd.household.v1+json"})
	public CleaningPlanDTO removeChore(@PathVariable String cleaningPlanId, @PathVariable String choreId) {
		return addLinks(cleaningPlanMapper.map(cleaningPlanService.removeChore(cleaningPlanId, choreId)));
	}

    private CleaningPlanDTO addLinks(CleaningPlanDTO cleaningPlan) {
        return addSelfLink(addAddChoreLink(addAddTaskLink(cleaningPlan))).getChores().stream()
            .map(chore -> addSelectChoreLink(cleaningPlan.getDatabaseId(), chore))
            .map(chore -> addDeleteChoreLink(cleaningPlan.getDatabaseId(), chore))
            .map(chore -> addSaveChoreLink(cleaningPlan.getDatabaseId(), chore))
            .reduce(cleaningPlan, (cp, c1) -> cp, (c1, cp) -> cp)
            .getTasks().stream()
            .map(task -> addSelectTaskLink(cleaningPlan.getDatabaseId(), task))
            .reduce(cleaningPlan, (cp, c1) -> cp, (c1, cp) -> cp);
    }

    private CleaningPlanDTO addSelfLink(CleaningPlanDTO cleaningPlan) {
        return (CleaningPlanDTO) cleaningPlan.add(Link.of("/api/cleaningPlans/" + cleaningPlan.getDatabaseId()));
    }

    private CleaningPlanDTO addAddChoreLink(CleaningPlanDTO cleaningPlan) {
        return (CleaningPlanDTO) cleaningPlan.add(Link.of("/api/cleaningPlans/" + cleaningPlan.getDatabaseId() + "/chores", "addChore"));
    }

    private CleaningPlanDTO addAddTaskLink(CleaningPlanDTO cleaningPlan) {
        return (CleaningPlanDTO) cleaningPlan.add(Link.of("/api/cleaningPlans/" + cleaningPlan.getDatabaseId() + "/tasks", "addTask"));
    }

    private ChoreDTO addSelectChoreLink(String cleaningPlanId, ChoreDTO chore) {
        return (ChoreDTO) chore.add(Link.of("/api/cleaningPlans/" + cleaningPlanId + "/chores/" + chore.getDatabaseId(), "select"));
    }

    private ChoreDTO addDeleteChoreLink(String cleaningPlanId, ChoreDTO chore) {
        return (ChoreDTO) chore.add(Link.of("/api/cleaningPlans/" + cleaningPlanId + "/chores/" + chore.getDatabaseId(), "delete"));
    }

    private ChoreDTO addSaveChoreLink(String cleaningPlanId, ChoreDTO chore) {
        return (ChoreDTO) chore.add(Link.of("/api/cleaningPlans/" + cleaningPlanId + "/chores/" + chore.getDatabaseId(), "save"));
    }

    private TaskDTO addSelectTaskLink(String cleaningPlanId, TaskDTO task) {
        return (TaskDTO) task.add(Link.of("/api/cleaningPlans/" + cleaningPlanId + "/tasks/" + task.getDatabaseId(), "select"));
    }
}
