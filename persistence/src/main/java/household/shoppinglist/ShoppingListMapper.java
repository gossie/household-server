package household.shoppinglist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ShoppingListMapper {
	
	private final ShoppingListItemMapper shoppingListItemMapper;

	public ShoppingList map(ShoppingListEntity shoppingList) {
		List<ShoppingListItem> items = shoppingList.getShoppingListItems().stream()
				.map(shoppingListItemMapper::map)
				.collect(Collectors.toList());
		
		return new ShoppingList(shoppingList.getId(), items);
	}
}
