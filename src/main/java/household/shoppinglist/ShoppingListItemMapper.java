package household.shoppinglist;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListItemMapper {

	public ShoppingListItem map(ShoppingListItemDTO shoppingListItem) {
		return new ShoppingListItem(shoppingListItem.getName(), shoppingListItem.isSelected());
	}
	
	public ShoppingListItemDTO map(ShoppingListItem shoppingListItem) {
		return new ShoppingListItemDTO(shoppingListItem.getName(), shoppingListItem.isSelected());
	}
}
