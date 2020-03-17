package household.shoppinglist;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

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
	public Mono<ShoppingListDTO> getShoppingList(@PathVariable Long id) {
		return Mono.just(createResource(shoppingListService.getShoppingList(id)))
            .flatMap(this::addLinks);
	}

    @PatchMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems/{itemId}", produces={"application/vnd.household.v2+json"})
    public Mono<ShoppingListDTO> toggleItem(@PathVariable Long id, @PathVariable Long groupId, @PathVariable Long itemId) {
        return Mono.just(createResource(shoppingListService.toggleItem(id, groupId, itemId)))
            .flatMap(this::addLinks);
    }

    @PutMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems/{itemId}", produces={"application/vnd.household.v2+json"})
    public Mono<ShoppingListDTO> editItem(@PathVariable Long id, @PathVariable Long groupId, @PathVariable Long itemId, @RequestBody ShoppingListItemDTO item) {
        return Mono.just(createResource(shoppingListService.editItem(id, groupId, itemId, shoppingListItemMapper.map(item))))
            .flatMap(this::addLinks);
    }

	@DeleteMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems", produces={"application/vnd.household.v2+json"})
	public Mono<ShoppingListDTO> removedSelectedItemsFromShoppingListGroup(@PathVariable Long id, @PathVariable Long groupId) {
		return Mono.just(createResource(shoppingListService.removeSelectedItemsFromShoppingListGroup(id, groupId)))
            .flatMap(this::addLinks);
	}

    @PostMapping(path="/{id}/shoppingListGroups", consumes={"application/vnd.household.v2+json"}, produces={"application/vnd.household.v2+json"})
    public Mono<ShoppingListDTO> addGroup(@PathVariable Long id, @RequestBody ShoppingListGroupDTO shoppingListGroup) {
        return Mono.just(createResource(shoppingListService.addShoppingListGroup(id, shoppingListGroupMapper.map(shoppingListGroup))))
            .flatMap(this::addLinks);
    }

    @PutMapping(path="/{id}/shoppingListGroups/{groupId}", produces={"application/vnd.household.v2+json"})
    public Mono<ShoppingListDTO> toggleGroup(@PathVariable Long id, @PathVariable Long groupId) {
        return Mono.just(createResource(shoppingListService.toggleShoppingListGroup(id, groupId)))
            .flatMap(this::addLinks);
    }

	@DeleteMapping(path="/{id}/shoppingListGroups/{groupId}", produces={"application/vnd.household.v2+json"})
    public Mono<ShoppingListDTO> deleteGroup(@PathVariable Long id, @PathVariable Long groupId) {
        return Mono.just(createResource(shoppingListService.deleteShoppingListGroup(id, groupId)))
            .flatMap(this::addLinks);
    }

	@PostMapping(path="/{id}/shoppingListGroups/{groupId}/shoppingListItems", consumes={"application/vnd.household.v2+json"}, produces={"application/vnd.household.v2+json"})
	public Mono<ShoppingListDTO> addItem(@PathVariable Long id, @PathVariable Long groupId, @RequestBody List<ShoppingListItemDTO> shoppingListItems) {
		List<ShoppingListItem> entities = shoppingListItems.stream().map(shoppingListItemMapper::map).collect(Collectors.toList());
		return Mono.just(createResource(shoppingListService.addShoppingListItems(id, groupId, entities)))
            .flatMap(this::addLinks);
	}

	private ShoppingListDTO createResource(ShoppingList shoppingList) {
		return shoppingListMapper.map(shoppingList);
	}

	@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="The group must not be deleted!")
    @ExceptionHandler(ShoppingListGroupNotDeletableException.class)
    public void handleException() {

    }

    private Mono<ShoppingListDTO> addLinks(ShoppingListDTO shoppingList) {
        return addShoppingListSelfLink(shoppingList)
            .flatMap(this::addCreateShoppingListGroupLink)
            .flatMapIterable(ShoppingListDTO::getShoppingListGroups)
            .flatMap(group -> addGroupToggleLink(shoppingList.getDatabaseId(), group))
            .flatMap(group -> addCreateItemLink(shoppingList.getDatabaseId(), group))
            .flatMap(group -> addClearItemsLink(shoppingList.getDatabaseId(), group))
            .flatMap(group -> addDeleteGroupLink(shoppingList.getDatabaseId(), group))
            .flatMap(group -> addItemLinks(shoppingList, group))
            .collect(() -> shoppingList, (a, b) -> {});
    }

    private Flux<ShoppingListItemDTO> addItemLinks(ShoppingListDTO shoppingList, ShoppingListGroupDTO group) {
	    return Flux.concat(group.getShoppingListItems()
            .stream()
            .map(item -> addItemToggleLink(shoppingList.getDatabaseId(), group.getDatabaseId(), item))
            .collect(Collectors.toList()));
    }

    private Mono<ShoppingListDTO> addShoppingListSelfLink(ShoppingListDTO shoppingList) {
        return linkTo(methodOn(ShoppingListController.class).getShoppingList(shoppingList.getDatabaseId()))
            .withSelfRel()
            .toMono()
            .map(shoppingList::add)
            .map(ShoppingListDTO.class::cast);
    }

    private Mono<ShoppingListDTO> addCreateShoppingListGroupLink(ShoppingListDTO shoppingList) {
        return linkTo(methodOn(ShoppingListController.class).addGroup(shoppingList.getDatabaseId(), null))
            .withRel("add")
            .toMono()
            .map(shoppingList::add)
            .map(ShoppingListDTO.class::cast);
    }

    private Mono<ShoppingListGroupDTO> addGroupToggleLink(Long shoppingListId, ShoppingListGroupDTO group) {
        return linkTo(methodOn(ShoppingListController.class).toggleGroup(shoppingListId, group.getDatabaseId()))
            .withRel("toggle")
            .toMono()
            .map(group::add)
            .map(ShoppingListGroupDTO.class::cast);
    }

    private Mono<ShoppingListGroupDTO> addCreateItemLink(Long shoppingListId, ShoppingListGroupDTO group) {
        return linkTo(methodOn(ShoppingListController.class).addItem(shoppingListId, group.getDatabaseId(), null))
            .withRel("add")
            .toMono()
            .map(group::add)
            .map(ShoppingListGroupDTO.class::cast);
    }

    private Mono<ShoppingListGroupDTO> addClearItemsLink(Long shoppingListId, ShoppingListGroupDTO group) {
        return linkTo(methodOn(ShoppingListController.class).removedSelectedItemsFromShoppingListGroup(shoppingListId, group.getDatabaseId()))
            .withRel("clear")
            .toMono()
            .map(group::add)
            .map(ShoppingListGroupDTO.class::cast);
    }

    private Mono<ShoppingListGroupDTO> addDeleteGroupLink(Long shoppingListId, ShoppingListGroupDTO group) {
	    if (!Objects.equals(group.getName(), "Global")) {
            return linkTo(methodOn(ShoppingListController.class).deleteGroup(shoppingListId, group.getDatabaseId()))
                .withRel("delete")
                .toMono()
                .map(group::add)
                .map(ShoppingListGroupDTO.class::cast);
        } else {
	        return Mono.just(group);
        }
    }

    private Mono<ShoppingListItemDTO> addItemToggleLink(Long shoppingListId, Long shoppingListGroupId, ShoppingListItemDTO item) {
        return linkTo(methodOn(ShoppingListController.class).toggleItem(shoppingListId, shoppingListGroupId, item.getDatabaseId()))
            .withRel("toggle")
            .toMono()
            .map(item::add)
            .map(ShoppingListItemDTO.class::cast)
            .map(d -> linkTo(methodOn(ShoppingListController.class).editItem(shoppingListId, shoppingListGroupId, item.getDatabaseId(), null)))
            .map(b -> b.withRel("edit"))
            .flatMap(WebFluxLinkBuilder.WebFluxLink::toMono)
            .map(item::add)
            .map(ShoppingListItemDTO.class::cast);
    }

}
