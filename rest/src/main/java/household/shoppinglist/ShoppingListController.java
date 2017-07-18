package household.shoppinglist;

import static household.Constants.DEFAULT_MEDIA_TYPE;

import java.util.List;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shoppingLists")
@ExposesResourceFor(ShoppingListDTO.class)
@RequiredArgsConstructor
public class ShoppingListController {
	
    private final ShoppingListGroupDTOMapper shoppingListGroupMapper;
	private final ShoppingListItemDTOMapper shoppingListItemMapper;
	private final ShoppingListDTOMapper shoppingListMapper;
	private final ShoppingListService shoppingListService;
	private final ShoppingListResourceProcessor shoppingListResourceProcessor;
	
	@GetMapping(path="/{id}", produces={DEFAULT_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTO>> getShoppingList(@PathVariable Long id) {
		return ResponseEntity.ok(createResource(shoppingListService.getShoppingList(id)));
	}
	
	@PatchMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems/{itemId}", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTO>> toggleItem(@PathVariable Long id, @PathVariable Long groupId, @PathVariable Long itemId) {
		return ResponseEntity.ok(createResource(shoppingListService.toggleItem(id, groupId, itemId)));
	}
	
	@DeleteMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTO>> removedSelectedItemsFromShoppingListGroup(@PathVariable Long id, @PathVariable Long groupId) {
		return ResponseEntity.ok(createResource(shoppingListService.removeSelectedItemsFromShoppingListGroup(id, groupId)));
	}
	
	@PostMapping(path="/{id}/shoppingListGroups", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
    public ResponseEntity<Resource<ShoppingListDTO>> addGroup(@PathVariable Long id, @RequestBody ShoppingListGroupDTO shoppingListGroup) {
        return ResponseEntity.ok(createResource(shoppingListService.addShoppingListGroup(id, shoppingListGroupMapper.map(shoppingListGroup))));
    }
	
	@PostMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems", consumes={DEFAULT_MEDIA_TYPE}, produces={DEFAULT_MEDIA_TYPE})
	public ResponseEntity<Resource<ShoppingListDTO>> addItem(@PathVariable Long id, @PathVariable Long groupId, @RequestBody List<ShoppingListItemDTO> shoppingListItems) {
		List<ShoppingListItem> entities = shoppingListItems.stream().map(shoppingListItemMapper::map).collect(Collectors.toList());
		return ResponseEntity.ok(createResource(shoppingListService.addShoppingListItems(id, groupId, entities)));
	}
	
	private Resource<ShoppingListDTO> createResource(ShoppingList shoppingList) {
		Resource<ShoppingListDTO> resource = new Resource<ShoppingListDTO>(shoppingListMapper.map(shoppingList));
		return shoppingListResourceProcessor.process(resource);
	}
}
