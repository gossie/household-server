package household.foodplan;

import static household.Constants.DEFAULT_MEDIA_TYPE;
import static household.Constants.V1_MEDIA_TYPE;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/foodPlans")
@ExposesResourceFor(FoodPlanDTO.class)
@RequiredArgsConstructor
public class FoodPlanController {
	
	private final FoodPlanDTOMapper foodPlanMapper;
	private final FoodPlanService foodPlanService;
	private final FoodPlanResourceProcessor foodPlanResourceProcessor;
	
	@GetMapping(path="/{id}", produces={V1_MEDIA_TYPE, DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<FoodPlanDTO>> getFoodPlan(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(foodPlanService.getFoodPlan(id)));
	}
	
	@PutMapping(path="/{id}", produces={V1_MEDIA_TYPE, DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<FoodPlanDTO>> clear(@PathVariable Long id, @RequestBody FoodPlanDTO foodPlan) {
		return ResponseEntity.ok(createResource(foodPlanService.update(id, foodPlanMapper.map(foodPlan))));
	}

	@DeleteMapping(path="/{id}/meals", produces={V1_MEDIA_TYPE, DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<FoodPlanDTO>> clear(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(foodPlanService.clear(id)));
	}
	
	private Resource<FoodPlanDTO> createResource(FoodPlan foodPlan) {
		Resource<FoodPlanDTO> resource = new Resource<FoodPlanDTO>(foodPlanMapper.map(foodPlan));
		return foodPlanResourceProcessor.process(resource);
	}
}
