package household.cookbook;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CookbookResourceProcessor implements ResourceProcessor<Resource<CookbookDTO>> {

	private final EntityLinks entityLinks;
	
	@Override
	public Resource<CookbookDTO> process(Resource<CookbookDTO> resource) {
		CookbookDTO cookbook = resource.getContent();
		resource.add(entityLinks.linkForSingleResource(CookbookDTO.class, cookbook.getDatabaseId()).withSelfRel());
		resource.add(entityLinks.linkForSingleResource(CookbookDTO.class, cookbook.getDatabaseId()).slash("recipes").withRel("create"));
		
		
		cookbook.getRecipes().forEach(r -> {
			Link selfLink = entityLinks.linkForSingleResource(CookbookDTO.class, cookbook.getDatabaseId())
					.slash("recipes").slash(r.getDatabaseId())
					.withSelfRel();
			
			r.add(selfLink);
		});
		
		return resource;
	}

}
