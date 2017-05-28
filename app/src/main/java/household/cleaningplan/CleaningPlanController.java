package household.cleaningplan;

import static household.Constants.DEFAULT_MEDIA_TYPE;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class CleaningPlanController {
	
	private final CleaningPlanMapper cleaningPlanMapper;
	private final ChoreMapper choreMapper;
	private final CleaningPlanService cleaningPlanService;
	private final CleaningPlanResourceProcessor cleaningPlanResourceProcessor;
	
	@GetMapping(path="/{cleaningPlanId}", produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<CleaningPlanDTO>> getCleaningPlan(@PathVariable Long cleaningPlanId) {
		return ResponseEntity.ok(createResource(cleaningPlanService.getCleaningPlan(cleaningPlanId)));
	}
	
	@PatchMapping(path="/{cleaningPlanId}", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
	public ResponseEntity<Resource<CleaningPlanDTO>> updateCleaningPlan(@PathVariable Long cleaningPlanId, @RequestBody ChoreDTO chore) {
		return ResponseEntity.ok(createResource(cleaningPlanService.update(cleaningPlanId, choreMapper.map(chore))));
	}

	@PostMapping(path="/{cleaningPlanId}/chores", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<CleaningPlanDTO>> addChore(@PathVariable Long cleaningPlanId, @RequestBody ChoreDTO chore) {
		return ResponseEntity.ok(createResource(cleaningPlanService.addChore(cleaningPlanId, choreMapper.map(chore))));
	}

	@DeleteMapping(path="/{cleaningPlanId}/chores/{choreId}", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<CleaningPlanDTO>> removeChore(@PathVariable Long cleaningPlanId, @PathVariable Long choreId) {
		return ResponseEntity.ok(createResource(cleaningPlanService.removeChore(cleaningPlanId, choreId)));
	}
	
	private Resource<CleaningPlanDTO> createResource(CleaningPlanEntity cleaningPlan) {
		Resource<CleaningPlanDTO> resource = new Resource<CleaningPlanDTO>(cleaningPlanMapper.map(cleaningPlan));
		return cleaningPlanResourceProcessor.process(resource);
	}
}
