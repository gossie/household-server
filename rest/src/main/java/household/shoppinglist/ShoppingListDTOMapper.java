package household.shoppinglist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShoppingListDTOMapper {
	
	private final ShoppingListItemDTOMapper shoppingListItemMapper;

	public ShoppingListDTO map(ShoppingList shoppingList) {
		List<ShoppingListItemDTO> items = shoppingList.getShoppingListItems().stream()
				.map(shoppingListItemMapper::map)
				.collect(Collectors.toList());
		
		return new ShoppingListDTO(shoppingList.getId(), items);
	}
}
