package household.v1.shoppinglist;

import org.springframework.stereotype.Component;

import household.shoppinglist.ShoppingListDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShoppingListDTOV1Mapper {
	
	private final ShoppingListItemDTOV1Mapper shoppingListItemMapper;

	public ShoppingListDTOV1 map(ShoppingListDTO shoppingList) {
		return null;
	}
}
