package household.shoppinglist;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListResourceProcessor implements ResourceProcessor<Resource<ShoppingListDTO>> {

	private final EntityLinks entityLinks;
	
    @Override
    public Resource<ShoppingListDTO> process(Resource<ShoppingListDTO> resource) {
    	ShoppingListDTO shoppingList = resource.getContent();
    	resource.add(entityLinks.linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId()).withSelfRel());
    	resource.add(entityLinks.linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId()).slash("/shoppingListItems").withRel("add"));
    	resource.add(entityLinks.linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId()).slash("/shoppingListItems").withRel("clear"));
        return resource;
    }
}
