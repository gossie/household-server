package household.shoppinglist.persistence;

import java.util.List;
import java.util.stream.Collectors;

import household.shoppinglist.domain.ShoppingListGroup;
import household.shoppinglist.domain.ShoppingListItem;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
public class ShoppingListGroupEntityMapper {

    private final ShoppingListItemEntityMapper shoppingListItemMapper;

    ShoppingListGroupEntity map(ShoppingListGroup shoppingListGroup) {
        List<ShoppingListItemEntity> items = shoppingListGroup.getShoppingListItems().stream()
                .map(shoppingListItemMapper::map)
                .collect(Collectors.toList());

        return new ShoppingListGroupEntity(shoppingListGroup.getId(), shoppingListGroup.getName(), items);
    }

    ShoppingListGroup map(ShoppingListGroupEntity shoppingListGroup) {
        List<ShoppingListItem> items = shoppingListGroup.getShoppingListItems().stream()
                .map(shoppingListItemMapper::map)
                .collect(Collectors.toList());

        return new ShoppingListGroup(shoppingListGroup.getId(), shoppingListGroup.getName(), items);
    }
}
