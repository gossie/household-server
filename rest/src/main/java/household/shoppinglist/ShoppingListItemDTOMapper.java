package household.shoppinglist;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListItemDTOMapper {

	public ShoppingListItem map(ShoppingListItemDTO shoppingListItem) {
		return new ShoppingListItem(null, shoppingListItem.getName(), shoppingListItem.isSelected());
	}
	
	public ShoppingListItemDTO map(ShoppingListItem shoppingListItem) {
		return new ShoppingListItemDTO(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected());
	}
}
