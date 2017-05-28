package household.shoppinglist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListMapper {
	
	private final ShoppingListItemMapper shoppingListItemMapper;

	public ShoppingListDTO map(ShoppingListEntity shoppingList) {
		List<ShoppingListItemDTO> items = shoppingList.getShoppingListItems().stream()
				.map(shoppingListItemMapper::map)
				.collect(Collectors.toList());
		
		return new ShoppingListDTO(shoppingList.getId(), items);
	}
}
