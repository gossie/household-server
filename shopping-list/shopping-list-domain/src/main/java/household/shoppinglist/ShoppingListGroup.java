package household.shoppinglist;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingListGroup extends AbstractModel {

    private final String name;
    private final List<ShoppingListItem> shoppingListItems;

    ShoppingListGroup(Long id, String name, List<ShoppingListItem> shoppingListItems) {
        super(id);
        this.name = name;
        this.shoppingListItems = new ArrayList<>(shoppingListItems);
    }

    public String getName() {
        return name;
    }

    public void addShoppingListItem(ShoppingListItem shoppingListItem) {
        shoppingListItems.add(shoppingListItem);
    }

    public void toggle() {
        boolean deselected = shoppingListItems.stream()
            .anyMatch(item -> !item.isSelected());

        shoppingListItems.forEach(item -> item.setSelected(deselected));
    }

    public void toogleItem(Long shoppingListItemId) {
        shoppingListItems.stream()
                .filter(item -> Objects.equals(item.getId(), shoppingListItemId))
                .findFirst()
                .ifPresent(item -> item.setSelected(!item.isSelected()));
    }

    public void editItem(Long shoppingListItemId, ShoppingListItem changedItem) {
        shoppingListItems.stream()
                .filter(item -> Objects.equals(item.getId(), shoppingListItemId))
                .findFirst()
                .ifPresent(item -> {
                    item.setName(changedItem.getName());
                    item.setSelected(changedItem.isSelected());
                    item.setImage(changedItem.getImage());
                });
    }

    public void clearSelectedItems() {
        shoppingListItems.removeIf(ShoppingListItem::isSelected);
    }

    public List<ShoppingListItem> getShoppingListItems() {
        return Collections.unmodifiableList(shoppingListItems);
    }

    public Optional<ShoppingListItem> getShoppingListItem(Long itemId) {
        return shoppingListItems.stream()
            .filter(item -> Objects.equals(item.getId(), itemId))
            .findFirst();
    }
}
