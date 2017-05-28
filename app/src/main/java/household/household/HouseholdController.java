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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/households")
@ExposesResourceFor(HouseholdDTO.class)
@RequiredArgsConstructor
public class HouseholdController {

	private final HouseholdService householdService;
	private final HouseholdMapper householdMapper;
	private final HouseholdResourceProcessor householdResourceProcessor;
	
	@PostMapping(consumes={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<HouseholdDTO>> createHousehold() {
		return ResponseEntity.ok(createResource(householdService.createHousehold()));
	}
	
	@GetMapping(path="/{id}", produces={DEFAULT_MEDIA_TYPE})
	public HttpEntity<Resource<HouseholdDTO>> getHoushold(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(householdService.getHousehold(id)));
	}
	
	private Resource<HouseholdDTO> createResource(HouseholdEntity household) {
		Resource<HouseholdDTO> resource = new Resource<HouseholdDTO>(householdMapper.map(household));
		return householdResourceProcessor.process(resource);
	}
}
