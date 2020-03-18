package household.shoppinglist;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Base64;

@RequiredArgsConstructor
@Component
class ShoppingListItemDTOMapper {

	ShoppingListItem map(ShoppingListItemDTO shoppingListItem) {
	    byte[] image = StringUtils.hasText(shoppingListItem.getImage()) ? Base64.getDecoder().decode(shoppingListItem.getImage()) : null;
		return new ShoppingListItem(null, shoppingListItem.getName(), shoppingListItem.isSelected(), image);
	}

	ShoppingListItemDTO map(ShoppingListItem shoppingListItem) {
	    String image = shoppingListItem.getImage() != null ? Base64.getEncoder().encodeToString(shoppingListItem.getImage()) : null;
		return new ShoppingListItemDTO(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected(), image);
	}
}
