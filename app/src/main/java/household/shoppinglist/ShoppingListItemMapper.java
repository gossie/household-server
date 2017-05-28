package household.shoppinglist;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListItemMapper {

	public ShoppingListItemEntity map(ShoppingListItemDTO shoppingListItem) {
		return new ShoppingListItemEntity(shoppingListItem.getName(), shoppingListItem.isSelected());
	}
	
	public ShoppingListItemDTO map(ShoppingListItemEntity shoppingListItem) {
		return new ShoppingListItemDTO(shoppingListItem.getName(), shoppingListItem.isSelected());
	}
}
