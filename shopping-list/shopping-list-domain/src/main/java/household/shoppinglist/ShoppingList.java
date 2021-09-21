package household.shoppinglist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingList extends AbstractModel {

	private final List<ShoppingListGroup> shoppingListGroups;

    ShoppingList(Long id, List<ShoppingListGroup> shoppingListGroups) {
    	super(id);
		this.shoppingListGroups = new ArrayList<>(shoppingListGroups);
	}

	ShoppingList(Long id) {
		this(id, new ArrayList<>(Collections.singletonList(new ShoppingListGroup(null, "Global", Collections.emptyList()))));
	}

	public void clearAllSelectedItems() {
	    shoppingListGroups.stream()
            .map(ShoppingListGroup::getId)
            .forEach(this::clearSelectedItemsFromShoppingListGroup);
	}

	public void clearSelectedItemsFromShoppingListGroup(Long shoppingListGroupId) {
		determineShoppingListGroup(shoppingListGroupId).ifPresent(ShoppingListGroup::clearSelectedItems);
	}

    public void toggleGroup(Long shoppingListGroupId) {
        determineShoppingListGroup(shoppingListGroupId).ifPresent(ShoppingListGroup::toggle);
    }

	public void toggleItem(Long shoppingListGroupId, Long shoppingListItemId) {
        determineShoppingListGroup(shoppingListGroupId).ifPresent(group -> group.toogleItem(shoppingListItemId));
	}

    public void editItem(Long shoppingListGroupId, Long shoppingListItemId, ShoppingListItem item) {
        determineShoppingListGroup(shoppingListGroupId).ifPresent(group -> group.editItem(shoppingListItemId, item));
    }

    public void addShoppingListGroup(ShoppingListGroup group) {
        shoppingListGroups.add(group);
    }

	public void addShoppingListItem(Long shoppingListGroupId, ShoppingListItem shoppingListItem) {
		determineShoppingListGroup(shoppingListGroupId).ifPresent(group -> group.addShoppingListItem(shoppingListItem));
	}

	private Optional<ShoppingListGroup> determineShoppingListGroup(Long shoppingListGroupId) {
	    return shoppingListGroups.stream()
            .filter(group -> Objects.equals(group.getId(), shoppingListGroupId))
            .findFirst();
	}

	public List<ShoppingListGroup> getShoppingListGroups() {
		return Collections.unmodifiableList(shoppingListGroups);
	}

    public Optional<ShoppingListGroup> getShoppingListGroup(Long groupId) {
        return shoppingListGroups.stream()
            .filter(group -> Objects.equals(group.getId(), groupId))
            .findFirst();
    }

    public void deleteShoppingListGroup(Long shoppingListGroupId) {
        Optional<ShoppingListGroup> shoppingListGroup = determineShoppingListGroup(shoppingListGroupId);
        if(shoppingListGroup.isPresent()) {
            if(Objects.equals(shoppingListGroup.get().getName(), "Global")) {
                throw new ShoppingListGroupNotDeletableException();
            } else {
                shoppingListGroups.remove(shoppingListGroup.get());
            }
        }
    }
}
