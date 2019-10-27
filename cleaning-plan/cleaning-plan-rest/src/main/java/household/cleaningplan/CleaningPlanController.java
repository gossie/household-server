package household.cleaningplan;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/cleaningPlans")
@ExposesResourceFor(CleaningPlanDTO.class)
@CrossOrigin
@RequiredArgsConstructor
public class CleaningPlanController {

	private final CleaningPlanDTOMapper cleaningPlanMapper;
	private final ChoreDTOMapper choreMapper;
	private final CleaningPlanService cleaningPlanService;
	private final CleaningPlanResourceProcessor cleaningPlanResourceProcessor;

	@GetMapping(path="/{cleaningPlanId}", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<CleaningPlanDTO>> getCleaningPlan(@PathVariable Long cleaningPlanId) {
		return ResponseEntity.ok(createResource(cleaningPlanService.getCleaningPlan(cleaningPlanId)));
	}

	@PatchMapping(path="/{cleaningPlanId}/chores/{choreId}", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
	public ResponseEntity<Resource<CleaningPlanDTO>> updateChore(@PathVariable Long cleaningPlanId, @PathVariable Long choreId, @RequestBody ChoreDTO chore) {
		return ResponseEntity.ok(createResource(cleaningPlanService.update(cleaningPlanId, choreMapper.map(choreId, chore))));
	}

	@PostMapping(path="/{cleaningPlanId}/chores", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<CleaningPlanDTO>> addChore(@PathVariable Long cleaningPlanId, @RequestBody ChoreDTO chore) {
		return ResponseEntity.ok(createResource(cleaningPlanService.addChore(cleaningPlanId, choreMapper.map(chore))));
	}

	@DeleteMapping(path="/{cleaningPlanId}/chores/{choreId}", produces={"application/vnd.household.v1+json"})
	public HttpEntity<Resource<CleaningPlanDTO>> removeChore(@PathVariable Long cleaningPlanId, @PathVariable Long choreId) {
		return ResponseEntity.ok(createResource(cleaningPlanService.removeChore(cleaningPlanId, choreId)));
	}

	private Resource<CleaningPlanDTO> createResource(CleaningPlan cleaningPlan) {
		Resource<CleaningPlanDTO> resource = new Resource<CleaningPlanDTO>(cleaningPlanMapper.map(cleaningPlan));
		return cleaningPlanResourceProcessor.process(resource);
	}
}