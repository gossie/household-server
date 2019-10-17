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
        var cleaningPlan = resource.getContent();
        resource.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId()).withSelfRel());
        resource.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId()).slash("chores").withRel("add"));

        cleaningPlan.getChores().forEach(chore -> {
            chore.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId())
                .slash("chores")
                .slash(chore.getDatabaseId())
                .withRel("delete"));

            chore.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId())
                .slash("chores")
                .slash(chore.getDatabaseId())
                .withRel("select"));

            chore.add(entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId())
                .slash("chores")
                .slash(chore.getDatabaseId())
                .withRel("save"));
        });

        return resource;
    }

    private void addLink(CleaningPlanDTO cleaningPlan, ChoreDTO chore, String rel) {
        var link = entityLinks.linkForSingleResource(CleaningPlanDTO.class, cleaningPlan.getDatabaseId())
            .slash("chores")
            .slash(chore.getDatabaseId())
            .withRel(rel);

        chore.add(link);
    }
}
