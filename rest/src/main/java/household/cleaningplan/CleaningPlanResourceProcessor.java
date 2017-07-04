package household.cleaningplan;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CleaningPlanResourceProcessor implements ResourceProcessor<Resource<CleaningPlanDTO>> {

	private final EntityLinks entityLinks;
	
    @Override
    public Resource<CleaningPlanDTO> process(Resource<CleaningPlanDTO> resource) {
    	CleaningPlanDTO cleaningPlan = resource.getContent();
    	resource.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId()).withSelfRel());
    	resource.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId()).slash("chores").withRel("add"));
    	
    	cleaningPlan.getChores().forEach(c -> {
    		Link link = entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId())
    				.slash("chores")
    				.slash(c.getDatabaseId())
    				.withRel("delete");
    		
			c.add(link);
    	});
    	
        return resource;
    }
}
