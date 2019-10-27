package household.shoppinglist;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class ShoppingListGroupDTOMapper {

    // TODO: Test me
    
    private final ShoppingListItemDTOMapper shoppingListItemMapper;
    
    ShoppingListGroupDTO map(ShoppingListGroup group) {
        List<ShoppingListItemDTO> items = group.getShoppingListItems().stream()
                .map(shoppingListItemMapper::map)
                .collect(Collectors.toList());
        
        return new ShoppingListGroupDTO(group.getId(), group.getName(), items);
    }

    public ShoppingListGroup map(ShoppingListGroupDTO shoppingListGroup) {
        List<ShoppingListItem> items = Optional.ofNullable(shoppingListGroup.getShoppingListItems())
                .map(this::mapItems)
                .orElse(Collections.emptyList());
        
        return new ShoppingListGroup(shoppingListGroup.getDatabaseId(), shoppingListGroup.getName(), items);
    }
    
    private List<ShoppingListItem> mapItems(List<ShoppingListItemDTO> shoppingListItems) {
        return shoppingListItems
                .stream()
                .map(shoppingListItemMapper::map)
                .collect(Collectors.toList());
    }
    
}
