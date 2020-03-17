package household.shoppinglist;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.Base64;

@RequiredArgsConstructor
@Component
class ShoppingListItemDTOMapper {

	ShoppingListItem map(ShoppingListItemDTO shoppingListItem) {
	    byte[] image = Base64.getDecoder().decode(shoppingListItem.getImage());
		return new ShoppingListItem(null, shoppingListItem.getName(), shoppingListItem.isSelected(), image);
	}

	ShoppingListItemDTO map(ShoppingListItem shoppingListItem) {
	    String image = Base64.getEncoder().encodeToString(shoppingListItem.getImage());
		return new ShoppingListItemDTO(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected(), image);
	}
}
