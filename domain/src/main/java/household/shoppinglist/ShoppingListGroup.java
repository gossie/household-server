package household.shoppinglist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import household.AbstractModel;

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
    
    public void toogleItem(Long shoppingListItemId) {
        shoppingListItems.stream()
            .filter(item -> Objects.equals(item.getId(), shoppingListItemId))
            .findFirst()
            .ifPresent(item -> item.setSelected(!item.isSelected()));
    }
    
    public void clearSelectedItems() {
        shoppingListItems.removeIf(ShoppingListItem::isSelected);
    }
    
    public List<ShoppingListItem> getShoppingListItems() {
        return Collections.unmodifiableList(shoppingListItems);
    }
}
