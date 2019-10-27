package household.v1.shoppinglist;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListResourceProcessorV1 implements ResourceProcessor<Resource<ShoppingListDTOV1>> {

	private final EntityLinks entityLinks;
	
    @Override
    public Resource<ShoppingListDTOV1> process(Resource<ShoppingListDTOV1> resource) {
    	ShoppingListDTOV1 shoppingList = resource.getContent();
    	resource.add(entityLinks.linkForSingleResource(ShoppingListDTOV1.class, shoppingList.getDatabaseId()).withSelfRel());
    	resource.add(entityLinks.linkForSingleResource(ShoppingListDTOV1.class, shoppingList.getDatabaseId()).slash("/shoppingListItems").withRel("add"));
    	resource.add(entityLinks.linkForSingleResource(ShoppingListDTOV1.class, shoppingList.getDatabaseId()).slash("/shoppingListItems").withRel("clear"));
        return resource;
    }
}
