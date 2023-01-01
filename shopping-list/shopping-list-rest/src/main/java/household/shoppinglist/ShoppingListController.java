package household.shoppinglist;

import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shoppingLists")
@CrossOrigin
@RequiredArgsConstructor
public class ShoppingListController {

    // private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListController.class);

    private final ShoppingListGroupDTOMapper shoppingListGroupMapper;
	private final ShoppingListItemDTOMapper shoppingListItemMapper;
	private final ShoppingListDTOMapper shoppingListMapper;
	private final ShoppingListService shoppingListService;

	@GetMapping(path="/{id}", produces={"application/vnd.household.v2+json"})
	public ShoppingListDTO getShoppingList(@PathVariable Long id) {
		return addLinks(createResource(shoppingListService.getShoppingList(id)));
	}

    @PatchMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems/{itemId}", produces={"application/vnd.household.v2+json"})
    public ShoppingListDTO toggleItem(@PathVariable Long id, @PathVariable Long groupId, @PathVariable Long itemId) {
        return addLinks(createResource(shoppingListService.toggleItem(id, groupId, itemId)));
    }

    @PutMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems/{itemId}", produces={"application/vnd.household.v2+json"}, consumes={"application/vnd.household.v2+json"})
    public ShoppingListDTO editItem(@PathVariable Long id, @PathVariable Long groupId, @PathVariable Long itemId, @RequestBody ShoppingListItemDTO item) {
        return addLinks(createResource(shoppingListService.editItem(id, groupId, itemId, shoppingListItemMapper.map(item))));
    }

    @GetMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems/{itemId}", produces={"image/png", "image/jpeg"})
    public byte[] retrieveImage(@PathVariable Long id, @PathVariable Long groupId, @PathVariable Long itemId) {
	    return shoppingListService.getShoppingList(id).getShoppingListGroup(groupId)
                .flatMap(group -> group.getShoppingListItem(itemId))
                .map(item -> item.getImage())
                .orElseThrow();
    }

	@DeleteMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems", produces={"application/vnd.household.v2+json"})
	public ShoppingListDTO removedSelectedItemsFromShoppingListGroup(@PathVariable Long id, @PathVariable Long groupId) {
		return addLinks(createResource(shoppingListService.removeSelectedItemsFromShoppingListGroup(id, groupId)));
	}

    @PostMapping(path="/{id}/shoppingListGroups", consumes={"application/vnd.household.v2+json"}, produces={"application/vnd.household.v2+json"})
    public ShoppingListDTO addGroup(@PathVariable Long id, @RequestBody ShoppingListGroupDTO shoppingListGroup) {
        return addLinks(createResource(shoppingListService.addShoppingListGroup(id, shoppingListGroupMapper.map(shoppingListGroup))));
    }

    @PutMapping(path="/{id}/shoppingListGroups/{groupId}", produces={"application/vnd.household.v2+json"})
    public ShoppingListDTO toggleGroup(@PathVariable Long id, @PathVariable Long groupId) {
        return addLinks(createResource(shoppingListService.toggleShoppingListGroup(id, groupId)));
    }

	@DeleteMapping(path="/{id}/shoppingListGroups/{groupId}", produces={"application/vnd.household.v2+json"})
    public ShoppingListDTO deleteGroup(@PathVariable Long id, @PathVariable Long groupId) {
        return addLinks(createResource(shoppingListService.deleteShoppingListGroup(id, groupId)));
    }

	@PostMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems", consumes={"application/vnd.household.v2+json"}, produces={"application/vnd.household.v2+json"})
	public ShoppingListDTO addItem(@PathVariable Long id, @PathVariable Long groupId, @RequestBody List<ShoppingListItemDTO> shoppingListItems) {
		List<ShoppingListItem> entities = shoppingListItems.stream()
            .map(shoppingListItemMapper::map)
            .toList();

		return addLinks(createResource(shoppingListService.addShoppingListItems(id, groupId, entities)));
	}

	private ShoppingListDTO createResource(ShoppingList shoppingList) {
		return shoppingListMapper.map(shoppingList);
	}

	@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="The group must not be deleted!")
    @ExceptionHandler(ShoppingListGroupNotDeletableException.class)
    public void handleException() {

    }

    private ShoppingListDTO addLinks(ShoppingListDTO shoppingList) {
        return addShoppingListSelfLink(addCreateShoppingListGroupLink(shoppingList)).getShoppingListGroups().stream()
            .map(group -> addItemLinks(shoppingList, addDeleteGroupLink(shoppingList.getDatabaseId(), addClearItemsLink(shoppingList.getDatabaseId(), addCreateItemLink(shoppingList.getDatabaseId(), addGroupToggleLink(shoppingList.getDatabaseId(), group))))))
            .reduce(shoppingList, (s, g) -> s, (r, c) -> c);
    }

    private ShoppingListGroupDTO addItemLinks(ShoppingListDTO shoppingList, ShoppingListGroupDTO group) {
	    group.getShoppingListItems()
            .stream()
            .map(item -> imageLink(shoppingList.getDatabaseId(), group.getDatabaseId(), addItemToggleLink(shoppingList.getDatabaseId(), group.getDatabaseId(), item)))
            .toList();
        return group;
    }

    private ShoppingListDTO addShoppingListSelfLink(ShoppingListDTO shoppingList) {
        return (ShoppingListDTO) shoppingList.add(Link.of("/api/shoppingLists/" + shoppingList.getDatabaseId()));
    }

    private ShoppingListDTO addCreateShoppingListGroupLink(ShoppingListDTO shoppingList) {
        return (ShoppingListDTO) shoppingList.add(Link.of("/api/shoppingLists/" + shoppingList.getDatabaseId() + "/shoppingListGroups", "add"));
    }

    private ShoppingListGroupDTO addGroupToggleLink(Long shoppingListId, ShoppingListGroupDTO group) {
        return (ShoppingListGroupDTO) group.add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + group.getDatabaseId(), "toggle"));
    }

    private ShoppingListGroupDTO addCreateItemLink(Long shoppingListId, ShoppingListGroupDTO group) {
        return (ShoppingListGroupDTO) group.add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + group.getDatabaseId() + "/shoppingListItems", "add"));
    }

    private ShoppingListGroupDTO addClearItemsLink(Long shoppingListId, ShoppingListGroupDTO group) {
        return (ShoppingListGroupDTO) group.add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + group.getDatabaseId() + "/shoppingListItems", "clear"));
    }

    private ShoppingListGroupDTO addDeleteGroupLink(Long shoppingListId, ShoppingListGroupDTO group) {
	    if (!Objects.equals(group.getName(), "Global")) {
            return (ShoppingListGroupDTO) group.add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + group.getDatabaseId(), "delete"));
        } else {
	        return group;
        }
    }

    private ShoppingListItemDTO addItemToggleLink(Long shoppingListId, Long shoppingListGroupId, ShoppingListItemDTO shoppingListItem) {
        return (ShoppingListItemDTO) shoppingListItem
                .add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + shoppingListGroupId + "/shoppingListItems/" + shoppingListItem.getDatabaseId(), "toggle"))
                .add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + shoppingListGroupId + "/shoppingListItems/" + shoppingListItem.getDatabaseId(), "edit"));
    }

    private ShoppingListItemDTO imageLink(Long shoppingListId, Long shoppingListGroupId, ShoppingListItemDTO shoppingListItem) {
	    if (shoppingListItem.hasImage()) {
            return ((ShoppingListItemDTO) shoppingListItem.add(Link.of("/api/shoppingLists/" + shoppingListId + "/shoppingListGroups/" + shoppingListGroupId + "/shoppingListItems/" + shoppingListItem.getDatabaseId(), "image")))
                    .removeImage();
        }
	    return shoppingListItem;
    }

}
