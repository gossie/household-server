package household.shoppinglist;

import java.util.Objects;

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
        resource.add(entityLinks.linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId()).slash("/shoppingListGroups").withRel("add"));
        
        shoppingList.getShoppingListGroups().forEach(group -> addGroupLinks(shoppingList, group));
        
        return resource;
    }
    
    private void addGroupLinks(ShoppingListDTO shoppingList, ShoppingListGroupDTO group) {
        if(!Objects.equals(group.getName(), "Global")) {
            group.add(entityLinks
                    .linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId())
                    .slash("/shoppingListGroups/")
                    .slash(group.getDatabaseId())
                    .withRel("delete"));
        }
        
        group.add(entityLinks
                .linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId())
                .slash("/shoppingListGroups/")
                .slash(group.getDatabaseId())
                .slash("/shoppingListItems")
                .withRel("add"));
        
        group.add(entityLinks
                .linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId())
                .slash("/shoppingListGroups/")
                .slash(group.getDatabaseId())
                .slash("/shoppingListItems")
                .withRel("clear"));
        
        group.getShoppingListItems().forEach(item -> addItemLinks(shoppingList, group, item));
    }

    private void addItemLinks(ShoppingListDTO shoppingList, ShoppingListGroupDTO group, ShoppingListItemDTO item) {
        item.add(entityLinks
                .linkForSingleResource(ShoppingListDTO.class, shoppingList.getDatabaseId())
                .slash("/shoppingListGroups/")
                .slash(group.getDatabaseId())
                .slash("/shoppingListItems/")
                .slash(item.getDatabaseId())
                .withRel("toggle"));
    }
}
