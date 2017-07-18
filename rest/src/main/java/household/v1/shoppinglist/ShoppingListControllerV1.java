package household.v1.shoppinglist;

import static household.Constants.V1_MEDIA_TYPE;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import household.shoppinglist.ShoppingListController;
import household.shoppinglist.ShoppingListDTO;
import household.shoppinglist.ShoppingListGroupDTO;
import household.shoppinglist.ShoppingListItemDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shoppingLists")
@ExposesResourceFor(ShoppingListDTOV1.class)
@RequiredArgsConstructor
public class ShoppingListControllerV1 {
	
	private final ShoppingListController shoppingListController;
	private final ShoppingListResourceProcessorV1 shoppingListResourceProcessor;
	
	@GetMapping(path="/{id}", produces={V1_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTOV1>> getShoppingList(@PathVariable Long id) {
	    return createResource(shoppingListController.getShoppingList(id).getBody().getContent());
	}
	
	@PatchMapping(path="/{id}", consumes={V1_MEDIA_TYPE}, produces={V1_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTOV1>> updateShoppingList(@PathVariable Long id, @RequestBody ShoppingListItemDTOV1 shoppingListItem) {
	    Long groupId = null;
	    Long itemId = null;
	    
	    for(ShoppingListGroupDTO group : shoppingListController.getShoppingList(id).getBody().getContent().getShoppingListGroups()) {
	        for(ShoppingListItemDTO item : group.getShoppingListItems()) {
	            if(Objects.equals(shoppingListItem.getName(), item.getName())) {
	                groupId = group.getDatabaseId();
	                itemId = item.getDatabaseId();
	                break;
	            }
	        }
	    }
	    
	    return createResource(shoppingListController.toggleItem(id, groupId, itemId).getBody().getContent());
	}
	
	@DeleteMapping(path="/{id}/shoppingListItems", consumes={V1_MEDIA_TYPE}, produces={V1_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTOV1>> removedSelectedItemsFromShoppingList(@PathVariable Long id) {
	    return null;
	}
	
	@PostMapping(path="/{id}/shoppingListItems", consumes={V1_MEDIA_TYPE}, produces={V1_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTOV1>> addItem(@PathVariable Long id, @RequestBody List<ShoppingListItemDTOV1> shoppingListItems) {
	    Long groupId = shoppingListController.getShoppingList(id).getBody().getContent().getShoppingListGroups().get(0).getDatabaseId();
	    List<ShoppingListItemDTO> mappedItems = shoppingListItems.stream().map(this::mapItem).collect(Collectors.toList());
	    
        return createResource(shoppingListController.addItem(id, groupId, mappedItems).getBody().getContent());
	}
	
	private ShoppingListDTOV1 mapList(ShoppingListDTO shoppingList) {
	    List<ShoppingListItemDTOV1> items = shoppingList.getShoppingListGroups()
	            .stream()
	            .flatMap(group -> group.getShoppingListItems().stream())
	            .map(this::mapItem)
	            .collect(Collectors.toList());
	    
	    return new ShoppingListDTOV1(shoppingList.getDatabaseId(), items);
	}
	
	private ShoppingListItemDTOV1 mapItem(ShoppingListItemDTO item) {
        return new ShoppingListItemDTOV1(item.getName(), item.isSelected());
    }
	
	private ShoppingListItemDTO mapItem(ShoppingListItemDTOV1 item) {
        return new ShoppingListItemDTO(null, item.getName(), item.isSelected());
    }
    
    private ResponseEntity<Resource<ShoppingListDTOV1>> createResource(ShoppingListDTO shoppingList) {
        Resource<ShoppingListDTOV1> resource = new Resource<ShoppingListDTOV1>(mapList(shoppingList));
        return ResponseEntity.ok(shoppingListResourceProcessor.process(resource));
    }
}
